<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<script src="iview/echarts-en.common.js"></script>
<style>
    .ivu-pt-8 {
        padding-top: 8px !important;
    }
</style>
<div id="app" style="margin: 24px;overflow-x: hidden">
    <Row style="margin-left: -12px; margin-right: -12px;">
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>访问量</span></p>
                <tag color="cyan" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">日</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">25,800</span>
                <div style="height: 42px;" class="ivu-pt-8">
                    放数据
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            访问量
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            120万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>采购额</span></p>
                <tag color="blue" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">月</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">¥12,000</span>
                <div style="height: 42px;" class="ivu-pt-8">
                    <Tooltip content="60%已达标" style="width: 100%">
                        <i-Progress :percent="60"/>
                    </Tooltip>
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            总采购额
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            120万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>入库数量</span></p>
                <tag color="red" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">周</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">12,000</span>
                <div style="height: 42px;" class="ivu-pt-8">
                    放数据
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            总入库量
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            580万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>销售数量</span></p>
                <tag color="volcano" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">年</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">¥12,000</span>
                <div style="height: 42px;" class="ivu-pt-8">
                    数据
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            总销售量
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            580万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
    </Row>

    <Row>
        <i-col :xs="24" :sm="24" :md="24" :lg="12" :xl="10">
            <Card style="margin-top: 12px">
                <div slot="title" style="height: 40px;display: flex;align-items: center">
                    <Row>
                        <Icon type="ios-pie-outline"
                              color="rgb(25, 150, 255)" style="cursor: pointer"></Icon>
                        <span style="margin-left: 5px" class="ivu-pt-8">采购量</span>
                    </Row>
                </div>
                <div id="myChart" style="width: 100%;height:400px;"></div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="24" :md="24" :lg="12" :xl="14">
            <Card style="margin-top: 12px ;margin-left: 12px">
                <div slot="title">
                    <Row type="flex" justify="center" style="align-items: center;">
                        <i-col span="8">
                            <Icon type="ios-podium" color="rgb(25, 150, 255)" style="cursor: pointer"></Icon>
                            <span style="margin-left: 5px" class="ivu-pt-8">采购量</span>
                        </i-col>
                        <i-col span="16" style="text-align: right;">
                            <div class="ivu-pt-8">
                                <i-Select v-model="formInline.groupField" placeholder="请选择分组"
                                          style="width: 200px;text-align: left;" transfer @on-change="changSerchData">
                                    <i-option value="o.bill.supplier.name">供应商</i-option>
                                    <i-option value="o.bill.buyer.username">采购员</i-option>
                                    <i-option value="o.product.name">商品名称</i-option>
                                    <i-option value="o.product.producttype.name">商品类别</i-option>
                                    <i-option value="date_format(o.bill.vdate,'%Y年%m月')">月份</i-option>
                                </i-Select>
                            </div>
                        </i-col>
                    </Row>
                </div>
                <div id="myChartgraph" style="width: 100%;height:400px;"></div>
            </Card>
        </i-col>
    </Row>
    <Card style="margin-top: 12px">
        <div slot="title">
            <Icon type="md-trending-up" color="rgb(25, 150, 255)" style="cursor: pointer"></Icon>
            <span style="margin-left: 5px" class="ivu-pt-8">折线图</span>
        </div>
        <Row>
            <i-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <div id="myChartLine" style="width:100%;height:400px;"></div>
            </i-col>
        </Row>
    </Card>
</div>
<script>
    new Vue({
        el: '#app',
        data() {
            return {
                formInline: {
                    groupField: "date_format(o.bill.vdate,'%Y年%m月')",/*分组字段，默认供应商*/
                },
                ChartLeftShow: [],/*左边显示,*/
                ChartDate: [],/*图形数据*/
                ChartLeftShowLine: [],/*则线图*/
                ChartDateLine: [],
                ChartDateLineNameList: [],
            }
        },
        created() {
            this.getChartDate();
            this.getChartDateLine();
            this.$nextTick(() => {
                this.drawPie();
                this.drawGraph();
                this.drawLine();
            });
        },
        methods: {
            changSerchData() {
                this.getChartDate();
                this.drawPie();
                this.drawGraph();
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
            getChartDateLine() {/*则线图*/
                this.ChartLeftShowLine = []/*必须清空*/
                var $page = this;
                $.ajax({/*查询图形数据*/
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "Admin/Purchasebillitem/findChartsLine",
                    dataType: 'json',
                    traditional: true,//防止深度序列化
                    async: false,/*取消异步加载*/
                    success: function (result) {/*用了框架的*/
                        for (let i = 0; i < result.lindate.length; i++) {
                            result.lindate[i].areaStyle = {}/*添加一个样式*/
                        }
                        $page.ChartDateLine = result.lindate;
                        $page.ChartLeftShowLine = result.mouth;
                        $page.ChartDateLineNameList = result.nameList;
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
                        text: '采购数量折线图'
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
                        data: this.ChartDateLineNameList
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
                            data: this.ChartLeftShowLine,
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: this.ChartDateLine
                };
                myChart.setOption(option);
            },
        }
    })
</script>