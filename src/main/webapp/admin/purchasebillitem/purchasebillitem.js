new Vue({
    el: "#app",
    data: function () {
        return {
            title: "信息管理",
            formInline: {
                supplierId: '',/*供应商*/
                buyerId: '',/*采购员*/
                time: '',/*采购时间*/
                status: '',/*单据状态*/
                typesValue: [],/*商品类型*/
                groupField: 'o.bill.supplier.name',/*分组字段，默认供应商*/
            },
            columns: [
                {
                    title: '序号',
                    type: 'index',
                    width: 100,
                    align: 'center',
                    key: 'total'/*用于数据统计的用途，无其他用途*/
                },
                {
                    title: '供应商',
                    key: 'supplierName',
                    align: 'center',
                },
                {
                    title: '采购员',
                    key: 'buyerName',
                    align: 'center',
                },
                {
                    title: '产品名称',
                    key: 'productName',
                    align: 'center',
                },
                {
                    title: '产品类型',
                    key: 'productType',
                    align: 'center',
                },
                {
                    title: '采购日期',
                    key: 'vdate',
                    align: 'center',
                },
                {
                    title: '数量',
                    key: 'num',
                    align: 'center',
                },
                {
                    title: '单价',
                    key: 'price',
                    align: 'center',
                },
                {
                    title: '小计',
                    key: 'amount',
                    align: 'center',
                },
                {
                    title: '状态',
                    slot: 'status',
                    align: 'center',
                },
            ],
            PurchasebillitemData: [],
            supplierValue: [],/*所有供应商*/
            buyerValue: [],/*所有采购员*/
            productypeIdValue: [],/*所有最后一级产品类型型*/
            chartShow: false,
            ChartLeftShow: [],/*左边显示,*/
            ChartDate: [],/*图形数据*/
            typesData: [],/*类别的级联选择框*/

        }
    },
    created() {
        this.getFirstMenuData();
        this.getAllSupplier();/*所有供应商*/
        this.getAllBuyer()/*所有采购部的人员*/
        this.getAllProductType()/*s所有最后一级的商品类型*/
    },
    mounted() {/*在页面元素加载完成后在初始化*/
    },
    methods: {
        getAllProductType() {/*查询出所有的类型而不是分页*/
            var typesData;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebillitem/producttype/findAllProducttype",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    typesData = result;
                }
            });
            this.formattypesData(typesData);/*格式化数据*/
            this.typesData = typesData;
        },
        formattypesData(data) {/*格式化数据*/
            for (let i = 0; i < data.length; i++) {
                if (data[i].children && data[i].children.length > 0) {
                    data[i] = $.extend({}, data[i], {value: data[i].id.toString(), label: data[i].name});
                    this.formattypesData(data[i].children)
                } else {
                    data[i] = $.extend({}, data[i], {value: data[i].id.toString(), label: data[i].name});
                }
            }
        },
        showDataPic() {// 展示图形数据
            this.chartShow = true
            this.getChartDate();
            this.$nextTick(() => {
                this.drawPie();
                this.drawGraph();
                this.drawLine();
            });
        },
        getChartDate() {
            this.ChartLeftShow = []/*必须清空*/
            var $page = this;
            $.ajax({/*查询图形数据*/
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebillitem/findChartsPie",
                dataType: 'json',
                data: {
                    "supplierId": this.formInline.supplierId,
                    "buyerId": this.formInline.buyerId,
                    "status": this.formInline.status,
                    "time": this.formInline.time,
                    "productypeId": this.formInline.typesValue[this.formInline.typesValue.length - 1],
                    "groupField": this.formInline.groupField
                },
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.ChartDate = result;
                    for (let i = 0; i < result.length; i++) {
                        $page.ChartLeftShow.push(result[i].name);
                    }
                }
            });
        },
        drawPie() {/*饼图*/
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('myChart'));
            // 绘制图表
            var option = {
                title: {
                    text: '采购报表数据',
                    subtext: '测试数据',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        let list = []
                        let listItem
                        list.push(params.seriesName)
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '名称' +
                            '</span>&nbsp&nbsp' + params.name
                        )
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '总金额' +
                            '</span>&nbsp&nbsp' + params.value + "元"
                        )
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '数量' +
                            '</span>&nbsp&nbsp' + params.data.num
                        )
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '占比' +
                            '</span>&nbsp&nbsp' + params.percent + "%"
                        )
                        listItem = list.join('<br>')
                        return '<div class="showBox">' + listItem + '</div>'
                        //return params.name + ":" + params.value + "元 共" + params.data.num + "件商品 占比:" + params.percent + "%"
                    }
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: this.ChartLeftShow/*name的集合,和洗下面的name一致*/
                },
                series: [
                    {
                        name: '采购金额',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: this.ChartDate,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            }
            myChart.setOption(option);
        },
        drawGraph() {/*柱状图*/
            var myChart = echarts.init(document.getElementById('myChartgraph'));
            var dataAxis = this.ChartLeftShow;
            var data = this.ChartDate;
            var option = {
                title: {
                    text: '采购报表数据',
                    subtext: '测试数据'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        let list = []
                        let listItem
                        list.push(params.seriesName)
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '名称' +
                            '</span>&nbsp&nbsp' + params.name
                        )
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '总金额' +
                            '</span>&nbsp&nbsp' + params.value + "元"
                        )
                        list.push(
                            '<i style="display: inline-block;width: 10px;height: 10px;background: ' +
                            params.color +
                            ';margin-right: 5px;border-radius: 50%;}"></i><span style="width:70px; display:inline-block;">' +
                            '数量' +
                            '</span>&nbsp&nbsp' + params.data.num
                        )
                        listItem = list.join('<br>')
                        return '<div class="showBox">' + listItem + '</div>'
                        //return params.name + ":" + params.value + "元 共" + params.data.num + "件商品 占比:" + params.percent + "%"
                    }
                },
                xAxis: {
                    type: 'category',
                    data: dataAxis,
                    axisLabel: {
                        interval: 0
                    }
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    { // For shadow
                        type: 'bar',
                        itemStyle: {
                            color: 'rgba(0,0,0,0.05)'
                        },
                        barGap: '-100%',
                        barCategoryGap: '40%',
                        animation: false
                    },
                    {
                        name: "采购金额",
                        type: 'bar',
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}元'　　　　//这是关键，在需要的地方加上就行了
                        },
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#83bff6'},
                                    {offset: 0.5, color: '#188df0'},
                                    {offset: 1, color: '#188df0'}
                                ]
                            )
                        },
                        emphasis: {
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#2378f7'},
                                        {offset: 0.7, color: '#2378f7'},
                                        {offset: 1, color: '#83bff6'}
                                    ]
                                )
                            }
                        },
                        data: data
                    }
                ]
            };
            myChart.setOption(option);
        },
        drawLine() {
            var myChart = echarts.init(document.getElementById('myChartLine'));
            var option = {
                title: {
                    text: '堆叠区域图'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                legend: {
                    data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '邮件营销',
                        type: 'line',
                        stack: '总量',
                        areaStyle: {},
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: '联盟广告',
                        type: 'line',
                        stack: '总量',
                        areaStyle: {},
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: '视频广告',
                        type: 'line',
                        stack: '总量',
                        areaStyle: {},
                        data: [150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: '直接访问',
                        type: 'line',
                        stack: '总量',
                        areaStyle: {},
                        data: [320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name: '搜索引擎',
                        type: 'line',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        areaStyle: {},
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }
                ]
            };
            myChart.setOption(option);
        },
        handleSummary({columns, data}) {/*合计单元格*/
            const sums = {};
            columns.forEach((column, index) => {
                const key = column.key;
                if (index === 0) {
                    sums[key] = {
                        key,
                        value: "总价格"
                    };
                    return;
                }
                const values = data.map(item => Number(item[key]));
                if (!values.every(value => isNaN(value))) {
                    const v = values.reduce((prev, curr) => {
                        const value = Number(curr);
                        if (!isNaN(value)) {
                            return prev + curr;
                        } else {
                            return prev;
                        }
                    }, 0);
                    sums[key] = {
                        key,
                        value: v.toFixed(2) + ' 元'
                    };
                } else {
                    sums[key] = {
                        key,
                        value: 'N/A'
                    };

                }
            });

            return sums;
        },
        getAllSupplier() {/*所有供应商*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebillitem/supplier/findAll",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.supplierValue = result;
                }
            });
        },
        getAllBuyer() {/*所有采购员*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebillitem/buyer/findAll",
                dataType: 'json',
                data: {"deptName": "采购部"},
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.buyerValue = result;
                }
            });
        },
        getTime(Date) {
            this.formInline.time = Date;
        },
        click_enter() {/*键盘事件,调用查找方法*/
            this.handleSubmit();
        },
        handleSubmit() {
            this.getFirstMenuData()
        },
        getFirstMenuData() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebillitem/findAll",
                data: {
                    "supplierId": this.formInline.supplierId,
                    "buyerId": this.formInline.buyerId,
                    "status": this.formInline.status,
                    "time": this.formInline.time,
                    "productypeId": this.formInline.typesValue[this.formInline.typesValue.length - 1]
                },
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    if (result.msg) {/*操作失败，无权限*/
                        notice.error({
                            title: '通知提醒',
                            desc: result.msg,
                        });
                    } else {
                        $page.PurchasebillitemData = result;
                    }

                }
            });
        },
    }

});