<%--
  Created by IntelliJ IDEA.
  User: qymls
  Date: 2020/4/9
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/admin/public/public_source.jsp" %>
<html>
<head>
    <title>图标选择器</title>
</head>
<style>
    .ivu-icon-md-add {
        cursor: pointer
    }

    .max_model_icon .ivu-modal-body {
        max-height: 500px;
        overflow: auto;
    }

    .icon_style {
        float: left;
        margin: 6px 6px 6px 0;
        width: 90px;
        text-align: center;
        list-style: none;
        height: 90px;
        color: #5c6b77;
        transition: all .2s ease;
        position: relative;
        padding-top: 10px;
    }
</style>
<body>
<div id="app">
    <template v-if="chooseData !=''">
        <i-Input :prefix="chooseData" icon="md-add" style="width: 200px;" v-model="chooseData" @on-click="showIcon"/>
    </template>
    <template v-else>
        <i-Input icon="md-add" style="width: 200px;" v-model="chooseData" @on-click="showIcon"/>
    </template>
    <Modal v-model="iconModal" footer-hide class-name="max_model_icon">
        <div slot="header" style="text-align: center;">
            <i-input type="text" v-model="searchInput" placeholder="输入英文关键词搜索，比如 add" style="width: 260px;"
                     @on-change="search_icon"></i-input>
        </div>
        <template v-if="allIconData.length !=0">
            <a href="javascript:void(0)" class="icon_style" v-for="item in allIconData" @click="chooseIcon(item)">
                <Icon :type="item" size="25"></Icon>
                <p>{{item}}</p>
            </a>
        </template>
        <template v-else>
            <div style="text-align: center">
                <p>无符合要求的数据</p>
            </div>
        </template>
    </Modal>
</div>

<script type="module">
    import iconData from './admin/public/icons.js';

    new Vue({
        el: '#app',
        components: {
            iconData,
        },
        data() {
            return {
                iconModal: false,
                allIconData: [],
                chooseData: '',
                searchInput: '',
                title: '图标选择器'
            }
        },
        created() {
        },
        methods: {
            showIcon() {
                this.searchInput = '';
                this.iconModal = true;
                this.allIconData = iconData;
            },
            chooseIcon(iconChoose) {/*选择图标*/
                this.chooseData = iconChoose;
                this.iconModal = false;
                console.log(iconChoose)
            },
            search_icon() {
                var searchResult = [];
                for (let i = 0; i < iconData.length; i++) {
                    if (iconData[i].indexOf(this.searchInput) != -1) {
                        console.log(iconData[i].indexOf(this.searchInput))
                        searchResult.push(iconData[i])
                    }
                }
                this.allIconData = searchResult;
            }
        }
    })
</script>
</body>
</html>
