<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .page_class .ivu-icon {
        line-height: unset;
    }

    .roleTable_style .ivu-table-cell-expand .ivu-icon {
        line-height: unset;
    }

    .ivu-table .demo-table-info-cell-style {
        background-color: #2db7f5;
        color: #fff;
    }

    .header_close_style { /*Modal框右上角关闭按钮框*/
        font-size: 31px;
        color: #999;
        -webkit-transition: color .2s ease;
        transition: color .2s ease;
        position: relative;
        top: 1px;
        cursor: pointer;
    }

    .header_class_close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 8px;
        top: 8px;
        overflow: hidden;
        cursor: pointer;
    }
    .rolelook_style .ivu-modal-body {
        max-height: 390px;
        overflow: auto;
    }

    /*    ::-webkit-scrollbar { !*不要滚动条*!
            width: 0;
        }*/
</style>
<div id="app">

    <Card>
        <p slot="title">
            <Icon type="ios-list-box-outline" size="20"></Icon>
            {{title}}
        </p>

        <Row>
            <i-col span="3">
                <i-button type="primary" icon="md-add" @click="newAdd">添加</i-button>
                <Poptip
                        confirm
                        placement="right"
                        transfer
                        title="您确认删除这些信息吗?"
                        @on-ok="deleteRole">
                    <i-button v-if="rows.length>0" type="error" icon="ios-trash">删除</i-button>
                </Poptip>
            </i-col>

            <i-col span="21">
                <i-Form ref="formInline" :model="formInline" inline style="margin-left: 20px;" @submit.native.prevent>
                    <Form-Item prop="name">
                        <i-Input type="text" v-model="formInline.name" placeholder="请输入查找的名称" @on-enter="click_enter">
                            <Icon type="ios-menu" slot="prepend"></Icon>
                        </i-Input>
                    </Form-Item>

                    <Form-Item>
                        <i-Button type="info" icon="ios-search" @click="handleSubmit('formInline')">查找</i-Button>
                    </Form-Item>
                </i-Form>
            </i-col>

        </Row>

        <Row justify="center" align="middle">
            <div style="margin-top:20px" class="roleTable_style">
                <i-Table :columns="columns" :data="RoleData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row, index }" slot="action">
                        <Tooltip content="权限配置" transfer placement="right" style="cursor: pointer;">
                            <Icon type="md-cog" @click="addRole(row.id)"></Icon>
                        </Tooltip>
                    </template>
                    <template slot-scope="{ row, index }" slot="permission_list">
                        <div style="overflow:hidden;text-overflow:ellipsis">{{getPermissionName(row)}}</div>
                        <%--省略号--%>
                    </template>
                </i-Table>
            </div>
            <div style="margin: 10px;overflow: hidden">
                <div style="float: right;">
                    <Page :total="total" show-total :page-size="pageSize" :page-size-opts="[5,10,20]" :current="page"
                          show-sizer transfer show-elevator
                          @on-change="changePage" @on-page-size-change="sizeChange"
                          class-name="page_class" style="margin-top: 10px;"></Page>
                </div>
            </div>
            <Modal v-model="updateModel" class-name="vertical-center-modal" draggable
                   :scrollable="true" width="1250" :styles="{top: '20px'}" :closable="false">
                <div slot="header">
                    <div class="header_class_close" @click="close_modal">
                        <Icon type="ios-close" class="header_close_style"></Icon>
                    </div>
                    <p>添加信息</p>
                </div>
                <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" inline>
                    <Form-Item prop="id" v-show=false>
                        <input type="text" v-model="formValidate.id"/>
                    </Form-Item>
                    <Form-Item label="名称" prop="name">
                        <i-Input v-model="formValidate.name" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="编码" prop="sn">
                        <i-Input v-model="formValidate.sn" placeholder="请输入相关值"></i-Input>
                    </Form-Item>

                    <div slot="header">
                        <h2>
                            <Icon type="md-options"></Icon>
                            权限控制
                        </h2>
                    </div>
                    <Row>
                        <i-col span="12">
                            <div>
                                <i-Table ref="selection" :columns="columnSource" :data="sourceData" border height="539"
                                         @on-row-dblclick="changSetting" @on-select="changeSourceDataSelect"
                                         @on-select-cancel="cancelSourceDataSelect"
                                         @on-select-all="allSourceDataSelect"
                                         @on-select-all-cancel="allCancelSourceDataSelect">
                                </i-Table>
                            </div>
                        </i-col>
                        <i-col span="12">
                            <div>
                                <i-Table :columns="columnTraget" :data="TragetData" border height="539"
                                         class="tragetTable"
                                         @on-row-click="changSettingReturn">
                                </i-Table>
                            </div>
                        </i-col>

                    </Row>
                    <div style="margin: 10px;overflow: hidden">
                        <div style="float: left;">
                            <Page :total="roleSettingTotal" show-total :page-size="roleSettingPageSize"
                                  :page-size-opts="[5,10,20]" :current="roleSettingPage"
                                  show-sizer transfer show-elevator
                                  @on-change="roleSettingChangePage" @on-page-size-change="roleSettingSizeChange"
                                  class-name="page_class" style="margin-top: 10px;"></Page>
                        </div>
                    </div>

                </i-Form>
                <div slot="footer" style="text-align: center">
                    <i-Button type="primary" @click="handleSubmitUpdate('formValidate')">确认保存</i-Button>
                    <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
                </div>
            </Modal>

        </Row>
        <%--权限控制--%>
        <Modal v-model="authority" draggable :scrollable="true" width="700" class="rolelook_style">
            <div slot="header">
                <h2>
                    <Icon type="md-options"></Icon>
                    菜单权限控制
                </h2>
            </div>
            <Row>
                <i-col span="12">
                    <div>
                        <Tree :data="authorityTree" transfer show-checkbox ref="tree"
                              @on-check-change="getAuthority" :render="renderContent"></Tree>
                    </div>
                </i-col>
                <Divider type="vertical" style="height: auto;width: 2px"/>
                <i-col span="12">
                    <div>
                        <Tree :data="authorityTreeShow" :render="renderContent"></Tree>
                    </div>
                </i-col>

            </Row>

            <div slot="footer">
                <i-button type="primary" @click="saveChang">确认保存</i-button>
            </div>

        </Modal>

    </Card>
    <script src="admin/role/role.js" type="module"></script>
</div>