export default {
    name: 'leftNavChildren',//name必须和下面的递归的组件名称一致
    template: "<div>" +
        "<Submenu v-for='item in parentItem' v-if='item.children&&item.children.length!==0' :name='item.name'>" +
        "<template slot='title'>" +
        "<Icon v-bind:type='item.icon'></Icon>" +
        "{{$t(item.label)}}" +
        "</template>" +
        "<left-Nav-Children :parent-item='item.children'>" +
        "</left-Nav-Children>" +
        "</Submenu>" +
        "<menu-item v-else :name='item.name'>" +
        "<Icon v-bind:type='item.icon'></Icon>" +
        "{{$t(item.label)}}" +
        "</menu-item>" +
        "</div>",
    props: {
        parentItem: {
            type: Array,
            default: () => {
            }
        }
    },

}
