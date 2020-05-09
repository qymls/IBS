<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<style>
    .ivu-exception {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        height: 80%;
        min-height: 500px;
    }

    .ivu-exception-img {
        -webkit-box-flex: 0;
        -ms-flex: 0 0 62.5%;
        flex: 0 0 62.5%;
        width: 62.5%;
        padding-right: 152px;
        zoom: 1;
    }

    .ivu-exception-img-element {
        float: right;
        width: 100%;
        max-width: 430px;
        height: 360px;
        background-repeat: no-repeat;
        background-position: 50% 50%;
        background-size: contain;
    }

    .ivu-exception-content {
        -webkit-box-flex: 1;
        -ms-flex: auto;
        flex: auto;
    }

    .ivu-exception-content-desc {
        margin-bottom: 16px;
        color: #808695;
        font-size: 20px;
        line-height: 28px;
    }
</style>
<div id="app">
    <div class="ivu-exception">
        <div class="ivu-exception-img">
            <div class="ivu-exception-img-element">
                <img src="admin/images/icon-403.svg" WIDTH="100%" HEIGHT="100%">
            </div>
        </div>
        <div class="ivu-exception-content"><h1>403</h1>
            <div class="ivu-exception-content-desc">抱歉，你无权访问该页面</div>
            <div class="ivu-exception-content-actions">
                <a href="Admin/Index/index"
                   class="ivu-btn ivu-btn-primary ivu-btn-large">
                    <span>返回首页</span></a>
            </div>
        </div>
    </div>
</div>
<script>
    new Vue({
        el: '#app',
        data() {
            return {}
        }

    })
</script>
