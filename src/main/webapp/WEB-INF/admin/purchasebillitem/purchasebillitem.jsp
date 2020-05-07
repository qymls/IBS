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
            <i-col span="24">
                <i-Form ref="formInline" :model="formInline" inline style="margin-left: 20px;" @submit.native.prevent>
                    <Form-Item prop="time">
                        <Date-Picker type="datetimerange" v-model="formInline.time" format="yyyy-MM-dd"
                                     placeholder="按照采购时间查询" transfer :editable="false" style="width: 230px"
                                     @on-change="getTime"></Date-Picker>
                    </Form-Item>
                    <Form-Item prop="supplierId">
                        <i-Select v-model="formInline.supplierId" placeholder="请选择供应商" clearable style="width: 200px">
                            <i-Option v-for="item in supplierValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item prop="buyerId">
                        <i-Select v-model="formInline.buyerId" placeholder="请选择采购员" clearable style="width: 200px">
                            <i-Option v-for="item in buyerValue" v-model="item.id">{{item.username}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item prop="productypeId">
                        <i-Select v-model="formInline.productypeId" placeholder="请选择商品类型" clearable style="width: 200px">
                            <i-Option v-for="item in productypeIdValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item prop="status">
                        <i-Select v-model="formInline.status" placeholder="请选择单据状态" clearable style="width: 200px">
                            <i-Option value="0">待审核</i-Option>
                            <i-Option value="1">已审核</i-Option>
                            <i-Option value="2">已过期</i-Option>
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
                <i-Table :columns="columns" :data="PurchasebillitemData" border max-height="650">
                    <template slot-scope="{ row, index }" slot="status">
                        <Tag color="error" v-if="row.status==0" style="cursor: pointer">待审核</Tag>
                        <Tag color="success" v-if="row.status==1" style="cursor: pointer">已审核</Tag>
                        <Tag color="#938E93" v-if="row.status==2" style="cursor: pointer">已作废</Tag>
                    </template>
                </i-Table>
            </div>
        </Row>

    </Card>
    <script src="admin/purchasebillitem/purchasebillitem.js" type="module"></script>
</div>