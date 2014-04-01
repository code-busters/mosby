// Some general UI pack related JS
// Extend JS String with repeat method
String.prototype.repeat = function (num) {
    return new Array(num + 1).join(this);
};

(function ($) {

    // Create event image uploading
    $(document).on('change', '.btn-file :file', function () {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var element = $('#background-block');
        readURL(this, element, 'background-image');
        input.trigger('fileselect', [numFiles, label]);
    });
    $(document).ready(function () {
        $('.btn-file :file').on('fileselect', function (event, numFiles, label) {

            var input = $(this).parents('.input-group').find(':text'),
                elem = $(this).parents('.form-group').find('.change-img-name'),
                log = numFiles > 1 ? numFiles + ' files selected' : label;

            if (input.length) {
                input.val(log);
                elem.text(log);
                elem.append("<span id='cancel-file' class='fui-cross'></span>");
            } else {
                if (log) alert(log);
            }

        });
    });
    $(document).on('click', "#cancel-file", function () {
        var control = $("#event-background:file");
        control.replaceWith(control = control.clone(true));
        var backup = $(this).closest('div').find('#backup-img').text();
        var image = $('#background-block');
        image.fadeOut(200, function () {
            image.css("background-image", "url(" + backup + ")");
            image.fadeIn(200);
        });
        $(this).closest('div').find(':text').val('');
        $(this).closest('div').find('.change-img-name').empty();
    });

    // Open logo
    $(document).on('change', '[id^="open-logo"]:file', function () {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var element = $(this).parents('.form-group').find('.logo');
        readURL(this, element, 'src');
        input.trigger('fileselect', [numFiles, label]);
    });
    $('[id^="open-logo"]:file').on('fileselect', function (event, numFiles, label) {
        var elem = $(this).parents('.form-group').find('.change-img-name'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        if (elem.length) {
            elem.text(log);
            elem.append("<span class='fui-cross cancel-upload'></span>");
        } else {
            if (log) alert(log);
        }

    });
    $(document).on('click', ".cancel-upload", function () {
        var control = $(this).parents('.form-group').find('[id^="open-logo"]:file');
        control.replaceWith(control = control.clone(true));
        var backup = $(this).parents('.form-group').find('#backup-img').text();
        var image = $(this).parents('.form-group').find('.logo');
        image.fadeOut(200, function () {
            image.attr('src', backup);
            image.fadeIn(200);
        });
        $(this).parents('.form-group').find('.change-img-name').empty();
    });


    // Contact info image uploading
    $(document).on('change', '#open-profile-img:file', function () {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var element = $('.user-profile-img');
        readURL(this, element, 'background-image');
        input.trigger('fileselect', [numFiles, label]);
    });
    $(document).ready(function () {
        $('#open-profile-img:file').on('fileselect', function (event, numFiles, label) {
            var elem = $(this).parents('.form-group').find('.change-img-name'),
                log = numFiles > 1 ? numFiles + ' files selected' : label;
            if (elem.length) {
                elem.text(log);
                elem.append("<span id='cancel-upload' class='fui-cross'></span>");
            } else {
                if (log) alert(log);
            }

        });
    });
    $(document).on('click', "#cancel-upload", function () {
        var control = $("#open-profile-img:file");
        control.replaceWith(control = control.clone(true));
        var backup = $(this).closest('div').find('#backup-img').text();
        var image = $('.user-profile-img');
        image.fadeOut(200, function () {
            image.css("background-image", "url(" + backup + ")");
            image.fadeIn(200);
        });
        $(this).closest('div').find('.change-img-name').empty();
    });

    function readURL(input, element, target) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                var elem = $(element);
                elem.fadeOut(200, function () {
                    if (target == 'src') {
                        elem.attr('src', e.target.result);

                    } else {
                        $(element).css("background-image", "url(" + e.target.result + ")");
                    }
                    elem.fadeIn(200);
                });
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    // Open navbar settings
    $(document).on('click', "#user-open", function () {
        $("#user-settings").removeClass('hide');
    });
    $('body').on('click', function (e) {
        $('#user-settings').each(function () {
            if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('#user-settings').has(e.target).length === 0) {
                $("#user-settings").addClass('hide');
            }
        });
    });
    $(window).scroll(function () {
        $("#user-settings").addClass('hide');
    });

    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });

    $(document).on('click', "#open-falldown", function () {
        var id = $(this).closest('.row').attr('id');
        var falldown = $(this).closest('.row').find("#falldown-" + id);
        falldown.slideToggle("slow");
    });

    $(document).on('click', "#open-falldown-search", function () {
        var falldown = $(this).closest('div').find("#falldown");
        falldown.slideToggle("slow");
    });

    $(document).on('change', "#checkbox-promo-code", function () {
        var elem = $(this).closest('.row').find("#promo-code-div");
        elem.toggle(300);
    });

    $('#google-plus-wrapper').click(function(event){
        $('#google-plus-button button').click();
    });

    $(document).on('change', 'input', function () {
        var save = $('#save-button');
        if (save.hasClass('hide')) {
            save.fadeOut(200, function () {
                save.removeClass('hide');
                save.fadeIn(200).animate(200);
            });
        }
    });

    // Ellipsis
    $(function () {

        // Todo list
        $(".todo").on('click', 'li', function () {
            $(this).toggleClass("todo-done");
        });

        // Custom Selects
        //    $("select[name='huge']").selectpicker({style: 'btn-hg btn-primary', menuStyle: 'dropdown-inverse'});
        $(".ticket-quantity").selectpicker({
            style: 'btn-default',
            menuStyle: 'dropdown-inverse'
        });
        $("#organizer").selectpicker({
            style: 'btn-default',
            menuStyle: 'dropdown-inverse'
        });
        $("select[name^='event_']").selectpicker({
            style: 'btn-default',
            menuStyle: 'dropdown-inverse'
        });
        $("select[name='gender']").selectpicker({
            style: 'btn-default',
            menuStyle: 'dropdown-inverse'
        });
        $("select[name='info']").selectpicker({
            style: 'btn-info'
        });

        // Tooltips
        $("[data-toggle=tooltip]").tooltip("show");

        // Tags Input
        $(".tagsinput").tagsInput();

        // jQuery UI Sliders
        var $slider = $("#slider");
        if ($slider.length) {
            $slider.slider({
                min: 1,
                max: 5,
                value: 2,
                orientation: "horizontal",
                range: "min"
            }).addSliderSegments($slider.slider("option").max);
        }

        var $verticalSlider = $("#vertical-slider");
        if ($verticalSlider.length) {
            $verticalSlider.slider({
                min: 1,
                max: 5,
                value: 3,
                orientation: "vertical",
                range: "min"
            }).addSliderSegments($verticalSlider.slider("option").max, "vertical");
        }

        // Placeholders for input/textarea
        $(":text, textarea").placeholder();

        // Focus state for append/prepend inputs
        $('.input-group').on('focus', '.form-control', function () {
            $(this).closest('.input-group, .form-group').addClass('focus');
        }).on('blur', '.form-control', function () {
            $(this).closest('.input-group, .form-group').removeClass('focus');
        });

        // Make pagination demo work
        $(".pagination").on('click', "a", function () {
            $(this).parent().siblings("li").removeClass("active").end().addClass("active");
        });

        $(".btn-group").on('click', "a", function () {
            $(this).siblings().removeClass("active").end().addClass("active");
        });

        $('.table .toggle-all').on('click', function () {
            var ch = $(this).find(':checkbox').prop('checked');
            $(this).closest('.table').find('tbody .checkbox :checkbox').checkbox(!ch ? 'check' : 'uncheck');
        });


        // Table: Add class row selected
        $('.table tbody :checkbox').on('check uncheck toggle', function (e) {
            var $this = $(this),
                check = $this.prop('checked'),
                toggle = e.type == 'toggle',
                checkboxes = $('.table tbody :checkbox'),
                checkAll = checkboxes.length == checkboxes.filter(':checked').length

            $this.closest('tr')[check ? 'addClass' : 'removeClass']('selected-row');
            if (toggle) $this.closest('.table').find('.toggle-all :checkbox').checkbox(checkAll ? 'check' : 'uncheck');
        });

        // Disable link clicks to prevent page scrolling
        $(document).on('click', 'a[href="#fakelink"]', function (e) {
            e.preventDefault();
        });

        // jQuery UI Datepicker
        $('[id*="datepicker"]').datepicker({
            showOtherMonths: true,
            selectOtherMonths: true,
            dateFormat: "yy-mm-dd",
            changeMonth: 1,
            changeYear: 1,
            minDate: '0d',
            yearRange: 'c:+3'
        }).prev('.btn').on('click', function (e) {
            e && e.preventDefault();
            $(this).closest('.input-datepicker').find('[id*="datepicker"]').focus();
        });
        $.extend($.datepicker, {
            _checkOffset: function (inst, offset, isFixed) {
                return offset
            }
        });

        $('#birthday').datepicker({
            showOtherMonths: true,
            selectOtherMonths: true,
            defaultDate: "1991-08-24",
            dateFormat: "yy-mm-dd",
            changeMonth: 1,
            changeYear: 1,
            yearRange: '1950:-16'
        }).prev('.btn').on('click', function (e) {
            e && e.preventDefault();
            $('#birthday').focus();
        });
        $.extend($.datepicker, {
            _checkOffset: function (inst, offset, isFixed) {
                return offset
            }
        });

        // Switch
        $("[data-toggle='switch']").wrap('<div class="switch" />').parent().bootstrapSwitch();

        // make code pretty
        window.prettyPrint && prettyPrint();

    });

})(jQuery);