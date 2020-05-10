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
                <i-button type="primary" icon="md-add" @click="newAdd" v-show="false">添加</i-button>
                <Poptip
                        confirm
                        placement="right"
                        transfer
                        title="您确认删除这些信息吗?"
                        @on-ok="deleteProductstock">
                    <i-button v-if="rows.length>0" type="error" icon="ios-trash" v-show="false">删除</i-button>
                </Poptip>
            </i-col>

            <i-col span="21">
                <i-Form ref="formInline" :model="formInline" inline style="margin-left: 20px;" @submit.native.prevent>
                    <Form-Item prop="depotId">
                        <i-Select v-model="formInline.depotId" placeholder="请选择仓库" clearable style="width: 200px">
                            <i-Option v-for="item in depotValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item>
                        <i-Button type="info" icon="ios-search" @click="handleSubmit('formInline')">查找</i-Button>
                    </Form-Item>
                </i-Form>
            </i-col>

        </Row>

        <Row justify="center" align="middle">
            <div style="margin-top:20px">
                <i-Table :columns="columns" :data="ProductstockData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row ,index}" slot="product" v-if="row.product">
                        {{row.product.name}}
                    </template>
                    <template slot-scope="{ row ,index}" slot="depot" v-if="row.depot">
                        {{row.depot.name}}
                    </template>
                    <template slot-scope="{ row, index }" slot="warning">
                        <Tag color="success" v-if="row.warning==true" style="cursor: pointer">已开启</Tag>
                        <Tag color="#938E93" v-if="row.warning==false" style="cursor: pointer">未开启</Tag>
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
                    <Form-Item label="数量" prop="num">
                        <i-Input v-model="formValidate.num" placeholder="请输入相关值" disabled></i-Input>
                    </Form-Item>
                    <Form-Item label="价格" prop="price">
                        <i-Input v-model="formValidate.price" placeholder="请输入相关值" disabled></i-Input>
                    </Form-Item>
                    <Form-Item label="最大库存" prop="topnum">
                        <i-Input v-model="formValidate.topnum" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="最小库存" prop="bottomnum">
                        <i-Input v-model="formValidate.bottomnum" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="库存警告" prop="warning">
                        <Radio-Group v-model="formValidate.warning">
                            <Radio label="true">
                                <Icon type="logo-apple"></Icon>
                                <span>开启警告</span>
                            </Radio>
                            <Radio label="false">
                                <Icon type="logo-android"></Icon>
                                <span>关闭警告</span>
                            </Radio>
                        </Radio-Group>
                    </Form-Item>
                    <Form-Item label="商品名称" prop="productId">
                        <i-Select v-model="formValidate.productId" placeholder="请选择产品" disabled>
                            <i-Option v-for="item in productValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item label="仓库名称" prop="depotId">
                        <i-Select v-model="formValidate.depotId" placeholder="请选择仓库" disabled>
                            <i-Option v-for="item in depotValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item>
                        <i-Button type="primary" @click="handleSubmitUpdate('formValidate')">确认</i-Button>
                        <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
                    </Form-Item>
                </i-Form>

            </Modal>

        </Row>

    </Card>
    <script src="admin/productstock/productstock.js" type="module"></script>
</div>