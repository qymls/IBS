new Vue({
    el: "#app",
    data: function () {
        return {
            title: "信息管理",
            rows: [],
            updateModel: false,
            formValidate: {
                id : '' ,
                num : '' ,
                amount : '' ,
                price : '' ,
                incomedate : '' ,
                warning : '' ,
                topnum : '' ,
                bottomnum : '' ,
                productId : '' ,
                depotId : '' ,
            
            },
            ruleValidate: {
                num : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                amount : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                price : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                incomedate : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                warning : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                topnum : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                bottomnum : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                productId : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                depotId : [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                        },
            formInline: {
                name: '',
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
                    title: 'num',
                    key: 'num',
                },
                                {
                    title: 'amount',
                    key: 'amount',
                },
                                {
                    title: 'price',
                    key: 'price',
                },
                                {
                    title: 'incomedate',
                    key: 'incomedate',
                },
                                {
                    title: 'warning',
                    key: 'warning',
                },
                                {
                    title: 'topnum',
                    key: 'topnum',
                },
                                {
                    title: 'bottomnum',
                    key: 'bottomnum',
                },
                                {
                    title: 'productId',
                    key: 'productId',
                },
                                {
                    title: 'depotId',
                    key: 'depotId',
                },
                            ],
            ProductstockData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
    },
    methods: {
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.updateModel = true;
            this.formValidate = data;
        },
        handleSubmitUpdate: function (name) {//提交方法
         var refs = this.$refs;
         refs['formValidate'].validate((valid)=>{
           if (valid) {
            var $page = this;
            var param = this.formValidate;
            var url;
            if (this.formValidate.id) {/*修改*/
                url = "Admin/Productstock/update"
                param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                } else {/*添加*/
                var url = "Admin/Productstock/save";
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
                $page.updateModel = false;
                $page.$Message.success("操作数据成功");
                $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                    }
                });
            } else {
                this.$Message.error("请按照表单要求填写");
                }
            })
        },

        handleReset: function (name) {//重置方法
            this.$refs['formValidate'].resetFields();
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
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Productstock/findAll",
                data: {
                    "name": this.formInline.name,
                    "currentPage": page,
                    "pageSize": pageSize
                },
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    $page.ProductstockData = result.content;
                    $page.total = result.totalElements;
                    $page.page = result.number + 1/*处理一个小bug*/
                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
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
                    $page.getFirstMenuData($page.page, $page.pageSize);/*修改完成后,刷新数据*/
                     notice.success({
                     title: "通知提醒",
                     desc: "删除成功",
                        });
                    $page.rows = [];
                }
            });
        }
    }

});