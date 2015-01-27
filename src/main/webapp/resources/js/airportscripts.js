jQuery(document).ready(function () {
    jQuery('.faq').popover({
        trigger: 'click',
        placement: 'top',
        html: 'true',
        content: jQuery('#faq-data').html(),
        template: '<div class="popover">'
        + '<h3 class="popover-title"></h3>'
        + '<div class="popover-content"></div>'
        + '</div>'
    });
    jQuery('.faq').click(function () {
        jQuery('a.list-group-item').hover(
            function () {
                jQuery(this).addClass('active');
            },
            function () {
                jQuery(this).removeClass('active');
            }
        );
    });
    jQuery('[resource*="/delete/"]').click(function () {
        var resourse = jQuery(this).attr('resource');
        jQuery('#deleteModal .btn-yes').attr('href', resourse);
    });
    jQuery('[data-toggle="tooltip"]').tooltip();
});