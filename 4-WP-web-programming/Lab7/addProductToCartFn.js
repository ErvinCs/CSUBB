jQuery(document).ready(function() {
        jQuery("#btn-buy").bind("click",function () {
            $.getJSON(
                'controller.php',
                {
                    action: 'addProductToCart',
                    productId: $('#prodIdCache').val()
                },
                createCart
            );
        });
});