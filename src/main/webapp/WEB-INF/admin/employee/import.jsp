<%--
  Created by IntelliJ IDEA.
  User: qymls
  Date: 2020/3/28
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    #app {
        height: calc(100% - 30px);
    }

    .ivu-table .demo-table-info-cell-style {
        background-color: #ff6600;
        color: #fff;
    }

</style>

<div id="app">

    <Card>
        <p slot="title">
            <Icon type="ios-list-box-outline" size="20"></Icon>
            {{title}}
        </p>

        <template>
            <Upload
                    type="drag"
                    action="Admin/Import/importEmployeeData"
                    :format="['xls','xlsx','csv']"
                    name="multipartFile"
                    :on-success="handleSuccess"
                    :on-format-error="handleFormatError"
            >
                <div style="padding: 20px 0">
                    <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                    <p>Click or drag files here to upload</p>
                </div>
            </Upload>
        </template>
        <Modal v-model="showMessage" title="数据信息" :styles="{display: 'flex','justify-content': 'center'}" width="auto"
               draggable footer-hide>
            <p style="color:#f44336;">注意：红色为有问题的数据，可以导出查看</p>
            <i-Table :columns="columns" :data="Data" height="520" style="margin-top: 20px">
                <div slot="footer" style="text-align: center">
                    <i-Button type="error" icon="ios-download-outline" @click="exportdata" v-if="faliList.length>0">
                        导出文件
                    </i-Button>
                    <i-Button @click="cancel" style="margin-left: 8px">关闭</i-Button>
                </div>
            </i-Table>
        </Modal>
    </Card>

</div>
<script type="module">
    new Vue({
        el: "#app",
        data: function () {
            return {
                title: "导入管理",
                showMessage: false,
                columns: [],
                Data: [],
                faliList: []
            }
        },
        created() {
        },
        methods: {
            handleSuccess(response, file, fileList) {/*上传成功*/
                var successList = response.successList;
                var faileList = response.failList;
                this.faliList = faileList;/*必须来就给*/
                var data;
                if (successList.length != 0) {
                    data = successList[0]
                } else if (faileList.length != 0) {
                    data = faileList[0]
                } else {
                    console.log("无数据上传")/*这种情况不可能*/
                }
                var columnsList = this.formatAllDate(data)/*得到上传的数据生成列和data*/
                this.columns = columnsList;/*获得了columns*/
                this.formatDepartMent(successList);
                this.formatDepartMent(faileList);
                this.Data = response.successList;
                var cellClassName = this.getCellClassName(data)
                for (let i = 0; i < faileList.length; i++) {
                    faileList[i] = $.extend({}, faileList[i], {cellClassName})
                    this.Data.push(faileList[i])/*添加进去合并，error显示是红色*/
                }
                this.showMessage = true
                this.$Notice.success({
                    title: "上传成功",
                    desc: "文件上传成功"
                })
            },
            formatDepartMent(data) {
                for (let i = 0; i < data.length; i++) {
                    if (data[i].department) {/*处理一些连表对象*/
                        data[i].department = data[i].department.name
                    }
                }
            },
            getCellClassName(data) {/*获取单元格字段样式*/
                var cellClassName = {}
                for (var k in data) {
                    cellClassName[k] = "demo-table-info-cell-style"
                }
                return cellClassName
            },
            handleFormatError(file) {/*文件格式不正确*/
                this.$Notice.error({
                    title: '文件格式错误',
                    desc: '文件 ' + file.name + ' 只能是xls,xlsx,csv格式的'
                });
            },
            formatAllDate(data) {
                var columnsList = []
                if (data) {
                    columnsList.push({
                        type: 'index',
                        width: 60,
                        align: 'center'
                    })
                    for (var k in data) {
                        var columnsdata = {}
                        columnsdata["title"] = k
                        columnsdata["key"] = k
                        columnsdata["align"] = "center"
                        columnsdata["width"] = "180"
                        columnsList.push(columnsdata)
                    }
                }
                return columnsList
            },
            exportdata() {/*导出*/
                window.location.href = "Admin/Import/downLoadFaileList"
            },
            cancel() {
                this.showMessage = false;
            },
        }
    })

</script>
