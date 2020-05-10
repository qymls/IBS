<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<script src="iview/echarts-en.common.js"></script>
<style>
    .page_class .ivu-icon {
        line-height: unset;
    }
    ::-webkit-scrollbar { /*不要滚动条*/
        width: 0;
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
                        <i-Select v-model="formInline.supplierId" placeholder="请选择供应商" clearable style="width: 200px" transfer>
                            <i-Option v-for="item in supplierValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item prop="buyerId">
                        <i-Select v-model="formInline.buyerId" placeholder="请选择采购员" clearable style="width: 200px" transfer>
                            <i-Option v-for="item in buyerValue" v-model="item.id">{{item.username}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item prop="typesValue">
                        <Cascader ref="cascader" :data="typesData" v-model="formInline.typesValue" filterable transfer
                                  placeholder="请选择商品类型"></Cascader>
                    </Form-Item>

                    <Form-Item prop="status">
                        <i-Select v-model="formInline.status" placeholder="请选择单据状态" clearable style="width: 200px" transfer>
                            <i-Option value="0">待审核</i-Option>
                            <i-Option value="1">已审核</i-Option>
                            <i-Option value="2">已过期</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item prop="groupField">
                        <i-Select v-model="formInline.groupField" placeholder="请选择分组状态" style="width: 200px" transfer>
                            <i-option value="o.bill.supplier.name">供应商</i-option>
                            <i-option value="o.bill.buyer.username">采购员</i-option>
                            <i-option value="o.product.name">商品名称</i-option>
                            <i-option value="o.product.producttype.name">商品类别</i-option>
                            <i-option value="date_format(o.bill.vdate,'%Y年%m月')">月份</i-option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item>
                        <Tooltip content="数据图表">
                            <Icon type="ios-pie-outline" size="30" style="cursor: pointer" @click="showDataPic"/>
                        </Tooltip>
                    </Form-Item>
                    <Form-Item>
                        <i-Button type="info" icon="ios-search" @click="handleSubmit('formInline')">查找</i-Button>
                    </Form-Item>
                </i-Form>

            </i-col>

        </Row>

        <Row justify="center" align="middle">
            <div style="margin-top:20px">
                <i-Table :columns="columns" :data="PurchasebillitemData" border max-height="650" show-summary
                         :summary-method="handleSummary">
                    <template slot-scope="{ row, index }" slot="status">
                        <Tag color="error" v-if="row.status==0" style="cursor: pointer">待审核</Tag>
                        <Tag color="success" v-if="row.status==1" style="cursor: pointer">已审核</Tag>
                        <Tag color="#938E93" v-if="row.status==2" style="cursor: pointer">已作废</Tag>
                    </template>
                </i-Table>
            </div>
        </Row>

    </Card>
    <Modal v-model="chartShow" draggable scrollable title="数据图表" width="950" footer-hide>
        <Tabs>
            <Tab-Pane label="数据饼图" icon="ios-pie-outline">
                <div id="myChart" style="width: 900px;height:400px;"></div>
            </Tab-Pane>
            <Tab-Pane label="数据柱状图" icon="ios-stats">
                <div id="myChartgraph" style="width: 900px;height:400px;"></div>
            </Tab-Pane>
            <Tab-Pane label="数据折线图" icon="md-trending-up">
                <div id="myChartLine" style="width: 900px;height:400px;"></div>
            </Tab-Pane>
        </Tabs>

    </Modal>
    <script src="admin/purchasebillitem/purchasebillitem.js" type="module"></script>
</div>