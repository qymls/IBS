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
                productypeId: ''/*商品类型*/
            },
            columns: [
                {
                    title: '序号',
                    type: 'index',
                    width: 100,
                    align: 'center',
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

        }
    },
    created() {
        this.getFirstMenuData();
        this.getAllSupplier();/*所有供应商*/
        this.getAllBuyer()/*所有采购部的人员*/
        this.getAllProductType()/*s所有最后一级的商品类型*/
    },
    methods: {
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
        getAllProductType() {/*所有供应商*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebillitem/producttype/findAllLastProducttype",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.productypeIdValue = result;
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
                    "productypeId": this.formInline.productypeId
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