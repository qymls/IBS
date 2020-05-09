<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .page_class .ivu-icon {
        line-height: unset;
    }

    .tx_style .ivu-icon {
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
                        @on-ok="deleteStockincomebill">
                    <i-button v-if="rows.length>0" type="error" icon="ios-trash">删除</i-button>
                </Poptip>
            </i-col>

            <i-col span="21">
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

                    <Form-Item prop="keeperId">
                        <i-Select v-model="formInline.keeperId" placeholder="请选择库管员" clearable style="width: 200px">
                            <i-Option v-for="item in keeperValue" v-model="item.id">{{item.username}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item prop="depotId">
                        <i-Select v-model="formInline.depotId" placeholder="请选择仓库" clearable style="width: 200px">
                            <i-Option v-for="item in depotValue" v-model="item.id">{{item.name}}</i-Option>
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
                <i-Table :columns="columns" :data="StockincomebillData" border max-height="650"
                         @on-selection-change="deleteRows"
                         @on-row-dblclick="updateModelShow">
                    <template slot-scope="{ row, index }" slot="keeper" v-if="row.keeper">
                        {{row.keeper.username}}
                    </template>
                    <template slot-scope="{ row, index }" slot="supplier" v-if="row.supplier">
                        {{row.supplier.name}}
                    </template>
                    <template slot-scope="{ row, index }" slot="inputUser" v-if="row.inputUser">
                        {{row.inputUser.username}}
                    </template>
                    <template slot-scope="{ row, index }" slot="auditor" v-if="row.auditor">
                        {{row.auditor.username}}
                    </template>
                    <template slot-scope="{ row, index }" slot="depot" v-if="row.depot">
                        {{row.depot.name}}
                    </template>
                    <template slot-scope="{ row, index }" slot="status">
                        <Tag color="error" v-if="row.status==0" style="cursor: pointer">待审核</Tag>
                        <Tag color="success" v-if="row.status==1" style="cursor: pointer">已审核</Tag>
                        <Tag color="#938E93" v-if="row.status==2" style="cursor: pointer">已作废</Tag>
                    </template>
                    <template slot-scope="{ row, index }" slot="audit">
                        <Tooltip content="审核入库" transfer placement="left">
                            <Icon type="md-contacts" size="24" style="cursor: pointer" v-if="row.status == 0"
                                  @click="showaudit(row)"/>
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
            <Modal v-model="updateModel" class-name="vertical-center-modal" width="1250" draggable :scrollable="true">
                <div slot="header">
                    <h2>
                        <Icon type="md-options"></Icon>
                        数据操作
                    </h2>
                </div>
                <i-Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" inline>
                    <Form-Item prop="id" v-show=false>
                        <input type="text" v-model="formValidate.id"/>
                    </Form-Item>
                    <Form-Item label="采购时间" prop="vdate">
                        <Date-Picker type="datetime" v-model="formValidate.vdate" format="yyyy-MM-dd HH:mm:ss"
                                     placeholder="请选择采购时间" transfer :editable="false"></Date-Picker>
                    </Form-Item>

                    <Form-Item label="供应商" prop="supplierId">
                        <i-Select v-model="formValidate.supplierId" placeholder="请选择供应商" clearable style="width: 200px">
                            <i-Option v-for="item in supplierValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>

                    <Form-Item label="库管员" prop="keeperId">
                        <i-Select v-model="formValidate.keeperId" placeholder="请选择库管员" clearable style="width: 200px">
                            <i-Option v-for="item in keeperValue" v-model="item.id">{{item.username}}</i-Option>
                        </i-Select>
                    </Form-Item>
                    <Form-Item label="仓库" prop="depotId">
                        <i-Select v-model="formValidate.depotId" placeholder="请选择仓库" clearable style="width: 200px">
                            <i-Option v-for="item in depotValue" v-model="item.id">{{item.name}}</i-Option>
                        </i-Select>
                    </Form-Item>
                </i-Form>
                <Card :bordered="false">
                    <template>
                        <p slot="title">明细编辑</p>
                        <i-button type="primary" icon="md-add" @click="addetail">添加</i-button>
                        <i-Table :columns="detailscolumns" :data="detaildata" style="margin-top: 10px;" height="375"
                                 class="details"
                                 border>
                            <template slot-scope="{ row, index }" slot="productName">
                                <template v-if="editIndex === index">
                                    <i-Select v-model="detaileformSave.id" style="width:100px"
                                              @on-change="selectProduct" transfer>
                                        <i-Option v-for="item in productAll" v-model="item.id">{{ item.name }}
                                        </i-Option>
                                    </i-Select>
                                </template>
                                <span v-else>{{getProductName(row.product.id)}}</span>
                            </template>
                            <template slot-scope="{ row, index }" slot="pic">
                                <template v-if="editIndex === index">
                                    <Avatar shape="square" :src="detaileformSave.pic" icon="ios-person" size="large"
                                            class="tx_style"/>
                                </template>
                                <span v-else><Avatar shape="square" :src="row.product.pic" icon="ios-person"
                                                     size="large" class="tx_style"/></span>
                            </template>
                            <template slot-scope="{ row, index }" slot="color">
                                <template v-if="editIndex === index">
                                    <Color-Picker v-model="detaileformSave.color" disabled/>
                                </template>
                                <span v-else><Color-Picker v-model="row.product.color" disabled/></span>
                            </template>
                            <template slot-scope="{ row, index }" slot="price">
                                <template v-if="editIndex === index">
                                    <Input-Number :min="0.0" :step="0.1" v-model="detaileformSave.price"
                                                  v-model="detaileformSave.price"
                                                  @on-change="priceChange(detaileformSave.price)"/>
                                </template>
                                <span v-else>{{row.price}}</span>
                            </template>

                            <template slot-scope="{ row, index }" slot="num">
                                <template v-if="editIndex === index">
                                    <Input-Number :min="0.0" :step="0.1" v-model="detaileformSave.num"
                                                  @on-change="numChange(detaileformSave.num)"/>
                                </template>
                                <span v-else>{{row.num}}</span>
                            </template>
                            <template slot-scope="{ row, index }" slot="amount">
                                <template v-if="editIndex === index">
                                    <Input-Number :min="0.0" :step="0.1" v-model="detaileformSave.amount" readonly/>
                                </template>
                                <span v-else>{{row.amount}}</span>
                            </template>
                            <template slot-scope="{ row, index }" slot="desc">
                                <template v-if="editIndex === index">
                                    <i-Input type="text" v-model="detaileformSave.desc"/>
                                </template>
                                <span v-else>{{row.desc}}</span>
                            </template>

                            <template slot-scope="{ row, index }" slot="action">
                                <div v-if="editIndex === index">
                                    <Tooltip content="保存" transfer :delay="3000">
                                        <Icon type="ios-checkmark" size="32" style="cursor: pointer;color: #57d660"
                                              @click="handleSave(index)"/>
                                    </Tooltip>
                                    <Tooltip content="取消" transfer :delay="3000">
                                        <Icon type="ios-close" size="28" style="cursor: pointer;color: red"
                                              @click="editIndex = -1" v-if="row.product.id"/>
                                    </Tooltip>
                                </div>
                                <div v-else>
                                    <Tooltip content="操作" transfer :delay="3000">
                                        <Icon type="ios-hammer" size="15" style="cursor: pointer"
                                              @click="handleEdit(row, index)"/>
                                    </Tooltip>

                                    <Tooltip content="删除" transfer style="margin-left: 12px;" :delay="3000">
                                        <Icon type="ios-trash" size="15" style="cursor: pointer"
                                              @click="deleteStockincomebillDetali(index)"/>
                                    </Tooltip>
                                </div>
                            </template>
                        </i-Table>
                    </template>
                </Card>
                <div slot="footer" style="text-align: center">
                    <i-Button type="primary" @click="handleSubmitUpdate('formValidate')" v-if="primarybuttonshow">
                        确认保存
                    </i-Button>
                    <i-Button @click="handleReset('formValidate')" style="margin-left: 8px" v-if="primarybuttonshow">
                        重置
                    </i-Button>
                    <i-Button type="info" v-if="quditbuttonshow" @click="auditsave">审核入库</i-Button>
                </div>

            </Modal>

        </Row>

    </Card>
    <script src="admin/stockincomebill/stockincomebill.js" type="module"></script>
</div>