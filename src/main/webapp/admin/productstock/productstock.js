new Vue({
    el: "#app",
    data: function () {
        const depotPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("仓库不能为空"));
            }
        };
        const productPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("商品不能为空"));
            }
        };
        const warningPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("请选择是否开启"));
            }
        };
        const numPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("数量不能为空"));
            }
        };
        const pricePlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("价格不能为空"));
            }
        };
        return {
            title: "信息管理",
            rows: [],
            updateModel: false,
            formValidate: {
                id: '',
                num: '',
                price: '',
                warning: '',
                topnum: '',
                bottomnum: '',
                productId: '',
                depotId: '',

            },
            ruleValidate: {
                num: [
                    {required: true, validator: numPlates, trigger: 'change'}
                ],
                price: [
                    {required: true, validator: pricePlates, trigger: 'change'}
                ],
                depotId: [
                    {required: true, validator: depotPlates, trigger: 'change'}
                ],
                productId: [
                    {required: true, validator: productPlates, trigger: 'change'}
                ],
                warning: [
                    {required: true, validator: warningPlates, trigger: 'change'}
                ],
            },
            formInline: {
                depotId: ''/*仓库*/
            },
            columns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '序号',
                    type: 'index',
                    width: 100,
                    align: 'center',
                },
                {
                    title: '产品名称',
                    slot: 'product',
                    align: "center"
                },
                {
                    title: '仓库名称',
                    slot: 'depot',
                    align: "center"
                },
                {
                    title: '价格',
                    key: 'price',
                    align: "center"
                },
                {
                    title: '数量',
                    key: 'num',
                    align: "center"
                },
                {
                    title: '小计',
                    key: 'amount',
                    align: "center"
                },
                {
                    title: '最大库存',
                    key: 'topnum',
                    align: "center"
                },
                {
                    title: '最小库存',
                    key: 'bottomnum',
                    align: "center"
                },
                {
                    title: '入库时间',
                    key: 'incomedate',
                    align: "center"
                },
                {
                    title: '警告',
                    slot: 'warning',
                    align: "center"
                },
            ],
            ProductstockData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
            productValue: [],
            depotValue: []
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
        this.getAllDepot();
    },
    methods: {
        getAllproduct() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Productstock/product/findAll",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.productValue = result;
                }
            });
        },
        getAllDepot() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Productstock/depot/findAll",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.depotValue = result;
                }
            });
        },
        click_enter() {/*键盘事件,调用查找方法*/
            this.handleSubmit();
        },
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.getAllDepot();
            this.getAllproduct();
            this.updateModel = true;
            data.depotId = data.depot.id
            data.productId = data.product.id
            data.warning = data.warning.toString();/*处理成字符串*/
            this.formValidate = data;
        },
        handleSubmitUpdate: function (name) {//提交方法
            var refs = this.$refs;
            refs[name].validate((valid) => {
                if (valid) {
                    var $page = this;
                    var messagePage = this.$Message;
                    var param = $.extend({}, this.formValidate)/*复制一份，因为要删除*/
                    if (param.depotId) {
                        param["depot.id"] = param.depotId
                    }
                    if (param.productId) {
                        param["product.id"] = param.productId
                    }
                    var url;
                    if (this.formValidate.id) {/*修改*/
                        url = "Admin/Productstock/update"
                        param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                    } else {/*添加*/
                        var url = "Admin/Productstock/save";
                        param.action = "save";
                    }
                    delete param["depotId"];
                    delete param["productId"];
                    delete param["incomedate"];
                    delete param["depot"];
                    delete param["product"];
                    $.ajax({
                        type: "POST",
                        contentType: "application/x-www-form-urlencoded",
                        url: url,
                        data: param,
                        dataType: "json",
                        async: false,/*取消异步加载*/
                        traditional: true,//防止深度序列化
                        success: function (result) {
                            if (result.msg) {/*操作失败，无权限*/
                                messagePage.error(result.msg);
                            } else {
                                $page.$Message.success('操作数据成功');
                                $page.updateModel = false;
                                $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                            }
                        }
                    });
                } else {
                    this.$Message.error("请按照表单要求填写");
                }
            })
        },

        handleReset: function (name) {//重置方法
            var ref = this.$refs;
            ref[name].resetFields();
        },

        handleSubmit() {
            this.getFirstMenuData(this.page, this.pageSize)
        },
        changePage(page) {
            this.page = page/*改变就设置值*/
            this.getFirstMenuData(page, this.pageSize);
        },
        sizeChange(pageSize) {/*改变就设置值*/
            this.pageSize = pageSize
            this.getFirstMenuData(this.page, pageSize);/*改变后page默认会变成1*/
        },

        getFirstMenuData(page, pageSize) {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Productstock/findAll",
                data: {
                    "depotId": this.formInline.depotId,
                    "currentPage": page,
                    "pageSize": pageSize
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
                        $page.ProductstockData = result.content;
                        $page.total = result.totalElements;
                        $page.page = result.number + 1/*处理一个小bug*/
                    }

                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.getAllDepot();
            this.getAllproduct();
            this.updateModel = true;
        },
        deleteRows: function (selection) {
            this.rows = [];
            for (let i = 0; i < selection.length; i++) {
                this.rows.push(selection[i].id)
            }
        },

        deleteProductstock() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Productstock/delete",
                data: {"ids": this.rows.toString()},
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
                        notice.success({
                            title: '通知提醒',
                            desc: "删除成功",
                        });
                        $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                        $page.rows = [];
                    }
                }
            });
        }
    }

});