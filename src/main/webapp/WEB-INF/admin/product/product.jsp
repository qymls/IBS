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
                        @on-ok="deleteProduct">
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
                <i-Table :columns="columns" :data="ProductData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row }" slot="producttype">
                        <strong>{{getproducttype(row)}}</strong>
                    </template>
                    <template slot-scope="{ row }" slot="unit">
                        <strong>{{getunit(row)}}</strong>
                    </template>
                    <template slot-scope="{ row }" slot="brand">
                        <strong>{{getbrand(row)}}</strong>
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
                    <Form-Item label="产品名称" prop="name">
                        <i-Input v-model="formValidate.name" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="颜色" prop="color">
                        <Color-Picker v-model="formValidate.color" />
                    </Form-Item>
                    <Form-Item label="成本价格" prop="costprice">
                        <i-Input v-model="formValidate.costprice" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="售卖价格" prop="saleprice">
                        <i-Input v-model="formValidate.saleprice" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="商品类型" prop="typesId">
                        <i-Select v-model="formValidate.typesId">
                            <i-Option value="beijing">New York</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item label="单位计量" prop="unitId">
                        <i-Input v-model="formValidate.unitId" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="品牌" prop="brandId">
                        <i-Input v-model="formValidate.brandId" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="图片" prop="pic">
                        <i-Input v-model="formValidate.pic" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item>
                        <i-Button type="primary" @click="handleSubmitUpdate('formValidate')">确认</i-Button>
                        <i-Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-Button>
                    </Form-Item>
                </i-Form>

            </Modal>

        </Row>

    </Card>
    <script src="admin/product/product.js" type="module"></script>
</div>