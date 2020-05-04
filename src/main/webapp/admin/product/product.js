new Vue({
    el: "#app",
    data: function () {
        const typesPlates = (rule, value, callback) => {/*异步验证菜单名称*/
            if (this.typesValue.length > 0) {/*这里的value无法获取，使用绑定的typesValue*/
                callback();
            } else {
                callback(new Error("类型不能为空，请选择类型"));
            }
        };
        const unitPlates = (rule, value, callback) => {/*异步验证菜单名称*/
            if (value) {
                callback();
            } else {
                callback(new Error("类型不能为空，请选择类型"));
            }
        };
        const brandPlates = (rule, value, callback) => {/*异步验证菜单名称*/
            if (value) {
                callback();
            } else {
                callback(new Error("品牌不能为空，请选择类型"));
            }
        };
        return {
            title: "信息管理",
            rows: [],
            updateModel: false,
            formValidate: {
                id: '',
                name: '',
                color: '',
                pic: '',
                costprice: '',
                saleprice: '',
                unitId: '',
                brandId: '',

            },
            ruleValidate: {
                name: [
                    {required: true, message: '请输入对应的值', trigger: 'blur'},
                ],
                typesValue: [
                    {required: true, validator: typesPlates, trigger: 'change'},
                ],
                unitId: [
                    {required: true, validator: unitPlates, trigger: 'change'},
                ],
                brandId: [
                    {required: true, validator: brandPlates, trigger: 'change'},
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
                    title: '产品名称',
                    key: 'name',
                },
                {
                    title: '颜色',
                    slot: 'show_color',
                },

                {
                    title: '成本价格',
                    key: 'costprice',
                },
                {
                    title: '售卖价格',
                    key: 'saleprice',
                },
                {
                    title: '商品类型',
                    slot: 'producttype',
                },
                {
                    title: '单位计量',
                    slot: 'unit',
                },
                {
                    title: '品牌',
                    slot: 'brand',
                },
                {
                    title: '图片',
                    slot: 'pic_show',
                    align: "center",
                    width: 100
                },
            ],
            ProductData: [],
            total: 0,
            page: 1,/*当前页默认为1*/
            pageSize: 5,/* 默认5条*/
            typesData: [],/*类别的级联选择框*/
            typesValue: [],
            unitList: [],
            brandList: [],
            visible: false,/*预览图片*/
            uploadfile: {
                status: '',
                showProgress: false,
                percentage: 0,
                defaultshow: true,
            }/*上传的文件属性*/
        }
    },
    created() {
        this.getFirstMenuData(this.page, this.pageSize);
    },
    methods: {
        getunitList() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/Systemdictionarydetail/findBySn",
                data: {sn: "productUnit"},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {
                    $page.unitList = result;
                }
            });
        },
        getbrandList() {
            var $page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/Systemdictionarydetail/findBySn",
                data: {sn: "productBrand"},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {
                    $page.brandList = result;
                }
            });
        },
        getAllProductType() {/*查询出所有的类型而不是分页*/
            var typesData;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/productTypes/findAll",
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
        getunit(row) {
            if (row.unit) {
                return row.unit.name
            } else {
                return "暂无信息"
            }
        },
        getbrand(row) {
            if (row.brand) {
                return row.brand.name
            } else {
                return "暂无信息"
            }
        },
        getproducttype(row) {
            if (row.producttype) {
                return row.producttype.name
            } else {
                return "暂无类别"
            }
        },
        click_enter() {/*键盘事件,调用查找方法*/
            this.handleSubmit();
        },
        updateModelShow(data) {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.$refs.cascader.clearSelect();/*清空级联选择器显示的数据*/
            this.getAllProductType();
            this.getunitList();
            this.getbrandList();
            this.updateModel = true;
            /*这里的计量单位和品牌都不能为空所以都会存在，直接赋值即可*/
            data.unitId = data.unit.id
            data.brandId = data.brand.id
            if (!data.pic) {/*处理图片上传时，数据库是NUll值，传递过来不显示的情况的*/
                data["pic"] = "";
            }
            this.formValidate = data;

            var typesValueList = this.getSelectAllPartnt(data.producttype.id);
            typesValueList.push(data.producttype.id)/*再加上本身的一级*/
            this.$nextTick(() => {/*必须放在这个里面，不然值不会刷新的*/
                for (let i = 0; i < typesValueList.length; i++) {/*需要转换成String类型才支持搜索功能*/
                    typesValueList[i] = typesValueList[i].toString();
                }
                this.typesValue = typesValueList;
            })
        },
        getSelectAllPartnt(id) {/*用于回显默认数据的*/
            var typesValueList = [];
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/productTypes/findAllParentByID",
                data: {"id": id},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    typesValueList = result;
                }
            });
            return typesValueList;
        },
        handleSubmitUpdate: function (name) {//提交方法
            var refs = this.$refs;
            refs[name].validate((valid) => {
                if (valid) {
                    var $page = this;
                    var messagePage = this.$Message;
                    var param = $.extend({}, this.formValidate)/*复制一份，因为要删除*/
                    param["producttype.id"] = this.typesValue[this.typesValue.length - 1]/*最后一个就是其id*/
                    param["unit.id"] = param["unitId"];
                    param["brand.id"] = param["brandId"];
                    var url;
                    if (this.formValidate.id) {/*修改*/
                        url = "Admin/Product/update"
                        param.action = "update"/*传递这个参数是配合 @ModelAttribute注解使用的，只用于修改*/
                    } else {/*添加*/
                        var url = "Admin/Product/save";
                        param.action = "save";
                    }
                    delete param["producttype"];
                    delete param["brand"];
                    delete param["unit"];
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
            this.$refs.cascader.clearSelect();/*清空级联选择器显示的数据*/
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
                url: "Admin/Product/findAll",
                data: {
                    "name": this.formInline.name,
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
                        $page.ProductData = result.content;
                        $page.total = result.totalElements;
                        $page.page = result.number + 1/*处理一个小bug*/
                    }

                }
            });
        },

        newAdd: function () {
            this.$refs['formValidate'].resetFields();/*清除model的表单数据,打开model就清空*/
            this.$refs.cascader.clearSelect();/*清空级联选择器显示的数据*/
            this.getAllProductType();
            this.getunitList();
            this.getbrandList();
            this.updateModel = true;
        },
        deleteRows: function (selection) {
            this.rows = [];
            for (let i = 0; i < selection.length; i++) {
                this.rows.push(selection[i].id)
            }
        },

        deleteProduct() {
            var $page = this;
            var notice = this.$Notice;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/delete",
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
        /*图片上传的相关方法*/
        upload_success(response, file, fileList) {
            this.formValidate.pic = response;
            this.uploadfile.status = 'finished';/*上传完成*/
        },

        handleProgress(event, file, fileList) {/*没有调试好，无法使用*/
            // 手动设置显示上传进度条 以及上传百分比
            // 调用监听 上传进度 的事件
            let uploadPercent = parseFloat(((event.loaded / event.total) * 100).toFixed(2))	// 保留两位小数，具体根据自己需求做更改
            this.uploadfile.percentage = uploadPercent/*进度*/
            console.log(uploadPercent)
        },
        handleView(name) {
            this.visible = true;
        },
        handleRemove(path) {
            var page = this;
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "Admin/Product/deleteImg",
                data: {"path": path},
                dataType: 'json',
                traditional: true,//防止深度序列化
                async: false,/*取消异步加载*/
                success: function (result) {/*用了框架的*/
                    page.formValidate.pic = '';/*删除了重置headimg*/
                    page.uploadfile = ''/*上传文件为初始值*/
                }
            });
        },
        handleFormatError(file) {
            this.$Notice.error({
                title: '文件类型错误',
                desc: '文件 ' + file.name + '类型错误,请选择jpg，jepg，png类型'
            });
            this.uploadfile = {
                status: '',
                showProgress: false,/*上传前就开始显示进度条了*/
                percentage: 0,
                defaultshow:true
            }
        },
        handleBeforeUpload() {/*因为上传单个，每次上传前回复到默认状态*/
            this.uploadfile = {
                status: 'start',
                showProgress: true,/*上传前就开始显示进度条了*/
                percentage: 0
            }
            return true;
        }
    }

});