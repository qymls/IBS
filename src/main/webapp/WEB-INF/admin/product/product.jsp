<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .page_class .ivu-icon {
        line-height: unset;
    }
</style>
<style>
    .process_style {
        margin-top: 23px;
    }

    .upload_style .ivu-icon {
        line-height: unset;
    }

    .demo-upload-list {
        display: inline-block;
        width: 60px;
        height: 60px;
        text-align: center;
        line-height: 60px;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        margin-right: 4px;
    }

    .imgLook_style .ivu-modal-body {
        max-height: 360px;
        overflow: auto;
    }

    ::-webkit-scrollbar { /*不要滚动条*/
        width: 0;
    }

    .demo-upload-list img {
        width: 100%;
        height: 100%;
    }

    .demo-upload-list-cover {
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0, 0, 0, .6);
    }

    .demo-upload-list:hover .demo-upload-list-cover {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .demo-upload-list-cover i {
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
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
                    <template slot-scope="{ row, index }" slot="pic_show">
                        <div class="demo-upload-list">
                            <img :src="row.pic">
                            <div class="demo-upload-list-cover">
                                <Icon type="ios-eye-outline" @click.native="handleView()"></Icon>
                            </div>
                        </div>
                    </template>
                    <template slot-scope="{ row, index }" slot="show_color">
                        <Color-Picker v-model="row.color" disabled/>
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
            <Modal title="添加信息" v-model="updateModel" class-name="vertical-center-modal" footer-hide draggable
                   :z-index="50">
                <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                    <Form-Item prop="id" v-show=false>
                        <input type="text" v-model="formValidate.id"/>
                    </Form-Item>
                    <Form-Item label="产品名称" prop="name">
                        <i-Input v-model="formValidate.name" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="颜色" prop="color">
                        <Color-Picker v-model="formValidate.color"/>
                    </Form-Item>
                    <Form-Item label="成本价格" prop="costprice">
                        <i-Input v-model="formValidate.costprice" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="售卖价格" prop="saleprice">
                        <i-Input v-model="formValidate.saleprice" placeholder="请输入相关值"></i-Input>
                    </Form-Item>
                    <Form-Item label="商品类型" prop="typesValue">
                        <Cascader ref="cascader" :data="typesData" v-model="typesValue" filterable></Cascader>
                    </Form-Item>
                    <Form-Item label="单位计量" prop="unitId">
                        <i-Select v-model="formValidate.unitId">
                            <i-Option v-for="item in unitList" :value="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item label="品牌" prop="brandId">
                        <i-Select v-model="formValidate.brandId">
                            <i-Option v-for="item in brandList" :value="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item label="图片" prop="pic" v-show="false">
                        <i-Input v-model="formValidate.pic"></i-Input>
                    </Form-Item>
                    <Form-Item label="商品图片">
                        <div class="demo-upload-list"
                             v-if="formValidate.pic||uploadfile.status==='start'||uploadfile.status==='finished'">
                            <template v-if="uploadfile.status === 'finished'||uploadfile.defaultshow">
                                <img :src="formValidate.pic">
                                <div class="demo-upload-list-cover">
                                    <Icon type="ios-eye-outline" @click.native="handleView()"></Icon>
                                    <Icon type="ios-trash-outline"
                                          @click.native="handleRemove(formValidate.pic)"></Icon>
                                </div>
                            </template>
                            <template v-else>
                                <i-Progress class="process_style" v-if="uploadfile.showProgress"
                                            :percent="uploadfile.percentage" hide-info
                                            :stroke-color="['#108ee9', '#87d068']"></i-Progress>
                            </template>
                        </div>
                        <Upload ref="upload" action="Admin/Product/upload" type="drag"
                                style="width: 58px;display: inline-block;"
                                class="upload_style" :show-upload-list="false" name="multipartFile" type="drag"
                                :format="['jpg','jpeg','png']"
                                :on-format-error="handleFormatError"
                                :on-progress="handleProgress"
                                :on-success="upload_success"
                                :before-upload="handleBeforeUpload"
                                :data="{'pic':formValidate.pic}"
                        >
                            <div style="width: 58px;height:58px;line-height: 58px;">
                                <Icon type="ios-camera" size="20"></Icon>
                            </div>
                        </Upload>
                        <Modal title="预览图片" class="imgLook_style" v-model="visible" draggable :z-index="100">
                            <img :src="formValidate.pic" v-if="visible" style="width: 100%">
                        </Modal>
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