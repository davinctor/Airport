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
    jQuery('[href="#role"]').click(function () {
        jQuery('[name="username"]').removeClass('show').addClass('hide');
        jQuery('[name="role"]').removeClass('hide').addClass('show');
    });
    jQuery('[href="#username"]').click(function () {
        jQuery('[name="username"]').removeClass('hide').addClass('show');
        jQuery('[name="role"]').removeClass('show').addClass('hide');
    });
    jQuery('#department-select').on('change', function () {
        jQuery('.staff-profile select.department').each(function () {
            jQuery(this).removeClass('show');
            jQuery(this).addClass('hide');
        });
        jQuery('#' + this.value).addClass('show');
        jQuery('#' + this.value).on('change', function () {
            jQuery('#' + this.value + ' option:first-child').prop('selected', true);
        });
    });
    jQuery('[type="submit"].btn-success').click(function () {
        var element = jQuery('#' + jQuery('#department-select').val() + ' option:first-child');
        if (element.prop('selected')) {
            var prnt = element.parent().parent();
            prnt.addClass('has-error');
            element.parent().focus();
            return false;
        }
    });
    jQuery('[data-dir="up"]').on('click', function () {
        var spinnerInputField = jQuery('.number-spinner #' + jQuery(this).attr('for'));
        var maxSpinner = parseInt(spinnerInputField.attr('max'), 10);
        var num = parseInt(spinnerInputField.val(), 10);
        if (num < maxSpinner)
            spinnerInputField.val(num + 1);
    });
    jQuery('[data-dir="dwn"]').on('click', function () {
        var spinnerInputField = jQuery('.number-spinner #' + jQuery(this).attr('for'));
        var minSpinner = parseInt(spinnerInputField.attr('min'), 10);
        var num = parseInt(spinnerInputField.val(), 10);
        if (num > minSpinner)
            spinnerInputField.val(num - 1);
    });
    jQuery('[id^="datepicker"]').datetimepicker({
        locale: 'en'
    });
    jQuery('.datetimepicker').each(function () {
        jQuery(this).datetimepicker({
            format: 'HH:mm'
        });
    });
});