<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="iview/iview.css">
    <script src="iview/vue.js"></script>
    <script src="iview/iview.min.js"></script>
    <script src="iview/jquery-3.4.1.min.js"></script>
    <style>
        #app { /*页面滑动*/
            overflow: auto;
            /*  height: calc(100% - 75px);*/
        }

        .ivu-table { /*table自适应*/
            height: auto;
        }

        /*上述可用于公共属性*/
        body { /*动画效果*/
            opacity: 0;
            animation: page-fade-in 1s forwards;
        }

        @keyframes page-fade-in {
            0% {
                opacity: 0
            }

            100% {
                opacity: 1
            }
        }

        /*美化滚动条*/
        ::-webkit-scrollbar {
            width: 7px;
        }

        ::-webkit-scrollbar-track {
            background-color: #f5f5f5;

            -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.1);

            border-radius: 5px;

        }

        ::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
            border-radius: 5px;
        }

        ::-webkit-scrollbar-button {
            background-color: #eee;
            display: none;
        }

        ::-webkit-scrollbar-corner {
            background-color: black;
        }

    </style>

</head>