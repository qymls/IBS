<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .page_class .ivu-icon {
        line-height: unset;
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
            <div style="margin-top:20px">
                <i-Table  :columns="columns" :data="RoleData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row, index }" slot="action">
                        <Tooltip content="权限配置" transfer placement="right" style="cursor: pointer;">
                            <Icon type="md-cog"></Icon>
                        </Tooltip>
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
            <Modal title="添加信息" v-model="updateModel" class-name="vertical-center-modal"  draggable
                   :scrollable="true" width="1050" :styles="{top: '20px'}">
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
                                    <i-Table :columns="columnSource" :data="sourceData" border height="520"
                                             @on-row-click="changSetting">
                                    </i-Table>
                                </div>
                            </i-col>
                            <i-col span="12">
                                <div>
                                    <i-Table :columns="columnTraget" :data="TragetData" border height="520"
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

    </Card>
    <script src="admin/role/role.js" type="module"></script>
</div>