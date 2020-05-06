new Vue({
    el: "#app",
    data: function () {
        const vdatePlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("采购时间不能为空"));
            }
        };
        const supplierPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("供应商不能为空"));
            }
        };
        const buyerPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("采购员不能为空"));
            }
        };
        return {
            title: "信息管理",
            rows: [],
            updateModel: false,
            formValidate: {
                id: '',
                vdate: '',
                supplierId: '',
                buyerId: '',
            },
            ruleValidate: {
                vdate: [
                    {required: true, validator: vdatePlates, trigger: 'change'}
                ],
                supplierId: [
                    {required: true, validator: supplierPlates, trigger: 'change'}
                ],
                buyerId: [
                    {required: true, validator: buyerPlates, trigger: 'change'}
                ],
            },
            formInline: {
                supplierId: '',/*供应商*/
                buyerId: '',/*采购员*/
                time: '',/*采购时间*/
                status: ''/*单据状态*/
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
                    title: '采购员',
                    slot: 'buyer',
                },
                {
                    title: '采购时间',
                    key: 'vdate',
                },
                {
                    title: '总金额',
                    key: 'totalamount',
                },
                {
                    title: '总数量',
                    key: 'totalnum',
                },
                {
                    title: '供应商',
                    slot: 'supplier',
                },

                {
                    title: '录入人',
                    slot: 'inputUser',
                },
                {
                    title: '录入时间',
                    key: 'inputtime',
                },
                {
                    title: '审核员',
                    slot: 'auditor',
                },
                {
                    title: '审核时间',
                    key: 'auditortime',
                },
                {
                    title: '单据状态',
                    slot: 'status',
                    align: 'center',
                },
            ],
            PurchasebillData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
            supplierValue: [],/*所有供应商*/
            buyerValue: [],/*所有采购员*/
            statusValue: [],/*审核状态，如使用字典类型再用*/
            detailscolumns: [
                {
                    title: '序号',
                    type: 'index',
                    width: 100,
                    align: 'center',
                },
                {
                    title: '商品',
                    slot: 'productName',
                    align: 'center',
                },
                {
                    title: '图片',
                    slot: 'pic',
                    align: 'center',
                },
                {
                    title: '颜色',
                    slot: 'color',
                    align: 'center',
                },
                {
                    title: '单价',
                    slot: 'price',
                    align: 'center',
                },
                {
                    title: '采购数量',
                    slot: 'num',
                    align: 'center',
                },
                {
                    title: '小计金额',
                    slot: 'amount',
                    align: 'center',
                },
                {
                    title: '描述',
                    slot: 'desc',
                    align: 'center',
                },
                {
                    title: '操作',
                    slot: 'action',
                    align: 'center',
                }
            ],
            detaildata: [],/*显示数据*/
            editIndex: -1,  // 当前聚焦的输入框的行数
            editName: '',  // 第一列输入框，当然聚焦的输入框的输入内容，与 data 分离避免重构的闪烁
            editAge: '',  // 第二列输入框
            editAddress: '',
            detaileformSave: {
                id: '',
                pic: '',
                color: '',
                price: 0.0,
                num: 0.0,
                amount: 0.0,
                desc: '',
            },
            rowsdetail: [],/*明细删除*/
            productAll: [],/*所有产品*/
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
        this.getAllSupplier();/*所有供应商*/
        this.getAllBuyer()/*所有采购部的人员*/
    },
    methods: {
        priceChange(price) {
            var tempamount = this.detaileformSave.num * price;
            if (tempamount > 0) {
                this.detaileformSave.amount = tempamount
            }

        },
        numChange(num) {
            var tempamount = this.detaileformSave.price * num;
            if (tempamount > 0) {
                this.detaileformSave.amount = tempamount
            }
        },
        selectProduct() {/*当选择产品时回填颜色图片*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebill/product/findOne",
                dataType: 'json',
                data: {"id": this.detaileformSave.id},
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.detaileformSave.pic = result.pic
                    $page.detaileformSave.color = result.color
                }
            });
        },
        getProductName(id) {/*通过id回显产品名称*/
            var productOne;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebill/product/findOne",
                dataType: 'json',
                data: {"id": id},
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    productOne = result;
                }
            });
            if (productOne) {
                if (productOne.id == id) {
                    return productOne.name
                }
            } else {
                return "未选择产品"
            }

        },
        getAllproductAll() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebill/product/findAll",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.productAll = result;
                }
            });
        },
        addetail() {/*添加明细*/
            this.scrollToBottom();/*添加数据滚动条到底部*/
            var newdata = {
                product: {id: 0, pic: '', color: ''},
                price: 0.0,
                num: 0,
                amount: 0.0,
                desc: ''
            }
            this.detaildata.push(newdata)
        },
        deletePurchasebillDetali(index) {/*删除列表中的*/
            this.detaildata.splice(index, 1);/*!*移除*!*/
        },
        handleEdit(row, index) {
            this.getAllproductAll()/*查询所有的产品*/
            this.detaileformSave.id = row.product.id
            this.detaileformSave.pic = row.product.pic;
            this.detaileformSave.color = row.product.color;
            this.detaileformSave.price = row.price;
            this.detaileformSave.num = row.num;
            this.detaileformSave.amount = row.amount;
            this.detaileformSave.desc = row.desc;
            this.editIndex = index;
        },
        handleSave(index) {
            if (this.detaileformSave.id == 0) {
                this.$Message.error('必须选择产品类型');
            } else {
                this.detaildata[index].product.id = this.detaileformSave.id;
                this.detaildata[index].product.pic = this.detaileformSave.pic;
                this.detaildata[index].product.color = this.detaileformSave.color;
                this.detaildata[index].price = this.detaileformSave.price;
                this.detaildata[index].num = this.detaileformSave.num;
                this.detaildata[index].amount = this.detaileformSave.amount;
                this.detaildata[index].desc = this.detaileformSave.desc;
                this.editIndex = -1;
            }
        },
        getAllSupplier() {/*所有供应商*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebill/supplier/findAll",
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
                url: "Admin/Purchasebill/buyer/findAll",
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
        ChangeDateFormat(paramDate) {/*日期转换"yyyy-MM-dd HH:mm:ss*/
            var date = paramDate
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDay = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var currentHour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var currentMinute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var currentSecond = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            return date.getFullYear() + '-' + month + "-" + currentDay + " " + currentHour + ":" + currentMinute + ":" + currentSecond;
        },
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.detaildata = []/*清空明细*/
            this.updateModel = true;
            this.formValidate.id = data.id;
            this.formValidate.vdate = data.vdate;
            this.formValidate.supplierId = data.supplier.id
            this.formValidate.buyerId = data.buyer.id
            this.detaildata = data.billitems/*采购单明细赋值*/

        },
        handleSubmitUpdate: function (name) {//提交方法
            var newvdate = '';
            if (this.formValidate.vdate instanceof Date) {/*这个组件有问题，一会是date一会是字符串*/
                newvdate = this.ChangeDateFormat(this.formValidate.vdate);/*必须处理一下的*/
            } else {
                newvdate = this.formValidate.vdate;
            }
            var refs = this.$refs;
            refs[name].validate((valid) => {
                if (valid) {
                    var $page = this;
                    var messagePage = this.$Message;
                    var param = $.extend({}, this.formValidate)/*复制一份，因为要删除*/
                    param['vdate'] = newvdate;
                    if (param.supplierId) {
                        param["supplier.id"] = param.supplierId
                    }
                    if (param.buyerId) {
                        param["buyer.id"] = param.buyerId
                    }
                    for (var i = 0; i < this.detaildata.length; i++) {
                        param["billitems[" + i + "].product.id"] = this.detaildata[i].product.id;
                        param["billitems[" + i + "].price"] = this.detaildata[i].price;
                        param["billitems[" + i + "].num"] = this.detaildata[i].num;
                        param["billitems[" + i + "].descs"] = this.detaildata[i].descs;
                    }
                    delete param["supplierId"];
                    delete param["buyerId"];
                    var url;
                    if (this.formValidate.id) {/*修改*/
                        url = "Admin/Purchasebill/update"
                        param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                    } else {/*添加*/
                        var url = "Admin/Purchasebill/save";
                        param.action = "save";
                    }
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
            this.detaildata = []/*清空明细*/
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
                url: "Admin/Purchasebill/findAll",
                data: {
                    "supplierId": this.formInline.supplierId,
                    "buyerId": this.formInline.buyerId,
                    "status": this.formInline.status,
                    "time": this.formInline.time,
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
                        $page.PurchasebillData = result.content;
                        $page.total = result.totalElements;
                        $page.page = result.number + 1/*处理一个小bug*/
                    }

                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.detaildata = []/*清空明细*/
            this.updateModel = true;
        },
        deleteRows: function (selection) {
            this.rows = [];
            for (let i = 0; i < selection.length; i++) {
                this.rows.push(selection[i].id)
            }
        },

        deletePurchasebill() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Purchasebill/delete",
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
        },
        scrollToBottom: function () {/*滚动条到底部的方法,出现滚动条之后下一次点击才会到底部*/
            this.$nextTick(() => {
                var container = $(".details .ivu-table-body");/*滚动条在谁身上就找谁*/
                container[0].scrollTop = container[0].scrollHeight
            })
        },
    }

});