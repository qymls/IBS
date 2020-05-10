<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .ivu-pt-8 {
        padding-top: 8px !important;
    }
</style>
<div id="app">
    <Row style="margin-left: -12px; margin-right: -12px;">
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>访问量</span></p>
                <tag color="cyan" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">日</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">25,800</span>
                <div style="height: 42px;" class="ivu-pt-8">
                    放数据
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            访问量
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            120万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>采购额</span></p>
                <tag color="blue" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">月</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">¥12,000</span>
                <div style="height: 42px;" class="ivu-pt-8">
                    <Tooltip content="60%已达标" style="width: 100%">
                        <i-Progress :percent="60"/>
                    </Tooltip>
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            总采购额
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            120万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>入库数量</span></p>
                <tag color="red" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">周</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">12,000</span>
                <div style="height: 42px;" class="ivu-pt-8">
                放数据
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                           总入库量
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            580万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>销售数量</span></p>
                <tag color="volcano" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">年</span>
                </tag>
                <span class="ivu-numeral" style="font-size: 30px;">¥12,000</span>
                <div style="height: 42px;" class="ivu-pt-8">
                   数据
                </div>
                <Divider style="margin: 8px 0px;"></Divider>
                <div>
                    <Row>
                        <i-col span="12">
                            总销售量
                        </i-col>
                        <i-col span="12" style="text-align: right;">
                            580万
                        </i-col>
                    </Row>
                </div>
            </Card>
        </i-col>
    </Row>
</div>
<script>
    new Vue({
        el: '#app',
        data() {
            return {}
        },
        created() {

        },
        methods: {}
    })
</script>