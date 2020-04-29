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
        <Modal v-model="showMessage" title="数据信息" :styles="{display: 'flex','justify-content': 'center'}" width="auto" draggable footer-hide>
            <i-Table :columns="columns" :data="Data" height="520">
                <div slot="footer" style="text-align: center">
                    <i-Button type="primary" @click="save">确认保存</i-Button>
                    <i-Button @click="cancel" style="margin-left: 8px">取消</i-Button>
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
            }
        },
        created() {
        },
        methods: {
            handleSuccess(response, file, fileList) {/*上传成功*/
                var columnsList = this.formatAllDate(response[0])/*得到上传的数据生成列和data*/
                this.columns = columnsList;/*获得了columns*/
                for (let i = 0; i < response.length; i++) {
                    if (response[i].department) {/*处理一些连表对象*/
                        response[i].department = response[i].department.name
                    }
                }
                this.Data = response;
                this.showMessage = true
                this.$Notice.success({
                    title: "上传成功",
                    desc: "文件上传成功"
                })


            },
            handleFormatError(file) {/*文件格式不正确*/
                this.$Notice.error({
                    title: '文件格式错误',
                    desc: '文件 ' + file.name + ' 只能是xls,xlsx,csv格式的'
                });
            },
            formatAllDate(data) {
                var columnsList = []
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
                return columnsList
            },
            save() {/*保存*/

            },
            cancel() {
                this.showMessage = false;
            },
        }
    })

</script>
