export default {
    name: 'leftNavChildrenShrinkMenu',//name必须和下面的递归的组件名称一致
    template: "<div>" +
        "<Dropdown v-for='item in parentItemMenu' v-if='item.children&&item.children.length!==0' placement='right-start'>" +
        "<Dropdown-item name='stop_click'>" +
        "<Icon :type='item.icon'></Icon>" +
        "<span class='span_icon'>{{$t(item.name)}}</span>" +
        "<Icon type='ios-arrow-forward'></Icon>" +
        "</Dropdown-item>" +
        "<Dropdown-Menu slot='list' style='text-align: left'>" +
        "<left-Nav-Children-Shrink-Menu :parent-item-menu='item.children'>" +
        "</left-Nav-Children-Shrink-Menu>" +
        "</Dropdown-Menu>" +
        "</Dropdown>" +
        "<Dropdown-Item v-else :name='item.name'>" +
        "<Icon :type='item.icon'></Icon>" +
        "<span class='span_icon'>{{$t(item.name)}}</span>" +
        "</Dropdown-Item>" +
        "</div>",
    props: {
        parentItemMenu: {
            type: Array,
            default: () => {
            }
        }
    },

}
