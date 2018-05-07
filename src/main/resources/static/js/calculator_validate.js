$(function () {
    $spec = $("#spec");
    $spec_msg = $("#spec_msg");
    $price = $("#price");
    $price_msg  = $("#price_msg");
    $min = $("#min");
    $min_msg = $("#min_msg");
    $max = $("#max");
    $max_msg = $("#max_msg");
    $sc_BTN =$("#single_cmt_BTN");
    $form_single = $("#single_product");

    //提交时检查是否有空字段
    $sc_BTN.click(function () {
        var flag = true;
        if ($spec.val() == ""){
            //检查规格是否为空
            $spec_msg.html("该项不能为空！");
            flag = false;
        }
        if ($price.val() == "") {
            //检查价格是否为空
            $price_msg.html("元，该项不能为空！");
            flag = false;
        }

        if ($min.val() == "") {
            //检查最小量是否为空
            $min_msg.html("支，该项不能为空！");
            flag = false;
        }
        if ($max.val() == ""){
            //检查 max是否为空
            $max_msg.html("支，该项不能为空！");
            flag = false;
        }

        if ($max.val() != "0"){
            var min = parseInt($min.val());
            var max = parseInt($max.val());
            if (min > max){
                $max_msg.html("支，该项不能小于" + min+"!");
                flag = false;
            }
        }

        if (flag){
            $form_single.submit();
        }
        return false;
    })
})