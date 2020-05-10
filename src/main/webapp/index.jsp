<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<div id="app">
    <Row style="margin-left: -12px; margin-right: -12px;">
        <i-col :xs="24" :sm="12" :md="12" :lg="12" :xl="6" style="padding-left: 12px; padding-right: 12px;">
            <Card>
                <p slot="title"><span>访问量</span></p>
                <tag color="cyan" slot="extra" style="cursor: pointer">
                    <span class="ivu-tag-text ivu-tag-color-white">日</span>
                </tag>
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