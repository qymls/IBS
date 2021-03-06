<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .page_class .ivu-icon {
        line-height: unset;
    }
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
                        @on-ok="deleteProducttype">
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
                <i-Table row-key="id" :columns="columns" :data="ProducttypeData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row, index }" slot="action">
                        <Tooltip content="删除" transfer placement="right" style="cursor: pointer;">
                            <Icon type="ios-trash" @click="deleteType(row,index)"></Icon>
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
            <Modal title="添加信息" v-model="updateModel" class-name="vertical-center-modal" footer-hide draggable>
                <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                    <Form-Item prop="id" v-show=false>
                        <input type="text" v-model="formValidate.id"/>
                    </Form-Item>
                    <Form-Item label="名称" prop="name">
                        <i-Input v-model="formValidate.name" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="描述" prop="descs">
                        <i-Input v-model="formValidate.descs" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="父级">
                        <Cascader ref="cascader" change-on-select :data="parentData"  v-model="parentValue"></Cascader>
                    </Form-Item>
                    <Form-Item>
                        <i-Button type="primary" @click="handleSubmitUpdate('formValidate')">确认</i-Button>
                        <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
                    </Form-Item>
                </i-Form>

            </Modal>
            <Modal v-model="delModel" width="360" draggable>
                <p slot="header" style="color:#f60;text-align:center">
                    <Icon type="ios-information-circle"></Icon>
                    <span>删除确认</span>
                </p>
                <div style="text-align:center">
                    <p style="color: red;margin-top: 5px;">即将删除</p>
                    <p v-for="item in delArray">{{item.name}}</p>
                    <p style="color: red;margin-top: 5px;">后无法恢复,请谨慎操作!</p>
                </div>
                <div slot="footer">
                    <i-Button type="error" size="large" long @click="deleteProducttype">删除</i-Button>
                </div>
            </Modal>

        </Row>

    </Card>
    <script src="admin/producttype/producttype.js" type="module"></script>
</div>