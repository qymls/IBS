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
        const keeperPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("库管员不能为空"));
            }
        };
        const depotPlates = (rule, value, callback) => {
            if (value) {
                callback();
            } else {
                callback(new Error("仓库不能为空"));
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
                keeperId: '',
                depotId: '',
            },
            ruleValidate: {
                vdate: [
                    {required: true, validator: vdatePlates, trigger: 'change'}
                ],
                supplierId: [
                    {required: true, validator: supplierPlates, trigger: 'change'}
                ],
                keeperId: [
                    {required: true, validator: keeperPlates, trigger: 'change'}
                ],
                depotId: [
                    {required: true, validator: depotPlates, trigger: 'change'}
                ],
            },
            formInline: {
                supplierId: '',/*供应商*/
                keeperId: '',/*库管员*/
                time: '',/*采购时间*/
                status: '',/*单据状态*/
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
                    title: '库管员',
                    slot: 'keeper',
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
                    title: '仓库',
                    slot: 'depot',
                },
                {
                    title: '单据状态',
                    slot: 'status',
                    align: 'center',
                },
                {
                    title: '审核',
                    slot: 'audit',
                    align: 'center',
                },
            ],
            StockincomebillData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
            supplierValue: [],/*所有供应商*/
            keeperValue: [],/*所有库管员*/
            statusValue: [],/*审核状态，如使用字典类型再用*/
            depotValue: [],
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
            detaileformSave: {
                id: '',
                pic: '',
                color: '',
                price: 0,
                num: 0,
                amount: 0,
                desc: '',
            },
            rowsdetail: [],/*明细删除*/
            productAll: [],/*所有产品*/
            primarybuttonshow: true,/*普通保存按钮展示*/
            quditbuttonshow: false/*审核保存按钮展示*/
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
        this.getAllSupplier();/*所有供应商*/
        this.getAllBuyer();/*所有采购部的人员*/
        this.getAllDepot();
    },
    methods: {
        auditsave() {/*审核方法*/
            this.handleSubmitUpdate('formValidate')/*提交审核表单*/
        },
        showaudit(data) {/*弹出审核框*/
            this.updateModelShow(data);
            this.primarybuttonshow = false;/*普通保存按钮展示*/
            this.quditbuttonshow = true;/*审核保存按钮展示*/
        },
        priceChange(price) {
            var tempamount = this.detaileformSave.num * price;
            if (tempamount > 0) {
                this.detaileformSave.amount = Number(tempamount.toFixed(2));
            }

        },
        numChange(num) {
            var tempamount = this.detaileformSave.price * num;
            if (tempamount > 0) {
                this.detaileformSave.amount = Number(tempamount.toFixed(2));
            }
        },
        selectProduct() {/*当选择产品时回填颜色图片*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Stockincomebill/product/findOne",
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
                url: "Admin/Stockincomebill/product/findOne",
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
                url: "Admin/Stockincomebill/product/findAll",
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
                price: 0,
                num: 0,
                amount: 0,
                desc: ''
            }
            if (this.detaildata.length == 0 || this.detaildata[this.detaildata.length - 1].product.id) {/*判断是否为空包括0,0也是空*/
                this.detaildata.push(newdata)
                this.handleEdit(newdata, this.detaildata.length - 1)/*调用一下handleEdit，打开最后一个*/
            } else {
                this.$Message.error('请先保存操作');
            }

        },
        deleteStockincomebillDetali(index) {/*删除列表中的*/
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
                url: "Admin/Stockincomebill/supplier/findAll",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.supplierValue = result;
                }
            });
        },
        getAllDepot() {/*所有供应商*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Stockincomebill/depot/findAll",
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.depotValue = result;
                }
            });
        },
        getAllBuyer() {/*所有库管员*/
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Stockincomebill/keeper/findAll",
                dataType: 'json',
                data: {"deptName": "仓管部"},
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.keeperValue = result;
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
            this.primarybuttonshow = true;/*普通保存按钮展示*/
            this.quditbuttonshow = false;/*审核保存按钮展示*/
            this.updateModel = true;
            this.formValidate.id = data.id;
            this.formValidate.vdate = data.vdate;
            this.formValidate.supplierId = data.supplier.id
            this.formValidate.keeperId = data.keeper.id
            this.formValidate.depotId = data.depot.id
            this.detaildata = $.extend([], data.billitems)/*采购单明细赋值*/

        },
        handleSubmitUpdate: function (name) {//提交方法
            if (this.detaildata.length == 0 || this.detaildata[this.detaildata.length - 1].product.id) {/*如果不为0或有值的话*/
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
                        if (param.keeperId) {
                            param["keeper.id"] = param.keeperId
                        }
                        if (param.depotId) {
                            param["depot.id"] = param.depotId
                        }
                        for (var i = 0; i < this.detaildata.length; i++) {
                            param["billitems[" + i + "].product.id"] = this.detaildata[i].product.id;
                            param["billitems[" + i + "].price"] = this.detaildata[i].price;
                            param["billitems[" + i + "].num"] = this.detaildata[i].num;
                            param["billitems[" + i + "].descs"] = this.detaildata[i].descs;
                        }
                        delete param["supplierId"];
                        delete param["keeperId"];
                        delete param["depotId"];
                        var url;
                        if (this.formValidate.id) {/*修改*/
                            if (this.primarybuttonshow) {/*判断是审核按钮还是普通修改*/
                                url = "Admin/Stockincomebill/update"
                            } else {
                                url = "Admin/Stockincomebill/audit"/*入库操作*/
                            }
                            param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                        } else {/*添加*/
                            var url = "Admin/Stockincomebill/save";
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
                                    if (result.faile) {
                                        $page.$Message.error(result.faile);/*如果遇到错误就显示出来，针对审核*/
                                    } else {
                                        $page.$Message.success('操作数据成功');
                                        $page.updateModel = false;
                                        $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                                    }
                                }
                            }
                        });
                    } else {
                        this.$Message.error("请按照表单要求填写");
                    }
                })
            } else {
                this.$Message.error('请先保存数据，在提交表单');
            }
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
                url: "Admin/Stockincomebill/findAll",
                data: {
                    "supplierId": this.formInline.supplierId,
                    "keeperId": this.formInline.keeperId,
                    "status": this.formInline.status,
                    "time": this.formInline.time,
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
                        $page.StockincomebillData = result.content;
                        $page.total = result.totalElements;
                        $page.page = result.number + 1/*处理一个小bug*/
                    }

                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.detaildata = []/*清空明细*/
            this.primarybuttonshow = true;/*普通保存按钮展示*/
            this.quditbuttonshow = false;/*审核保存按钮展示*/
            this.updateModel = true;
        },
        deleteRows: function (selection) {
            this.rows = [];
            for (let i = 0; i < selection.length; i++) {
                this.rows.push(selection[i].id)
            }
        },

        deleteStockincomebill() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Stockincomebill/delete",
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
                // 当滚动条从没有到有时，不加setTimeout滚动条将不会滚动到底部
                setTimeout(() => {
                    let overflowY = $(".details .ivu-table-body")[0];
                    if (!overflowY) {
                        return
                    }
                    overflowY.scrollTop = overflowY.scrollHeight + 34
                }, 50)
            })
        },
    }

});