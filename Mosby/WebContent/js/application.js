// Some general UI pack related JS
// Extend JS String with repeat method
String.prototype.repeat = function (num) {
	return new Array(num + 1).join(this);
};

(function ($) {

	$(document).on('change', '.btn-file :file', function () {
		var input = $(this),
			numFiles = input.get(0).files ? input.get(0).files.length : 1,
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		input.trigger('fileselect', [numFiles, label]);
	});

	$(document).ready(function () {
		$('.btn-file :file').on('fileselect', function (event, numFiles, label) {

			var input = $(this).parents('.input-group').find(':text'),
				log = numFiles > 1 ? numFiles + ' files selected' : label;

			if (input.length) {
				input.val(log);
			} else {
				if (log) alert(log);
			}

		});
	});
	
//	$(document).on('click', '.user-profile-img', function () {
//		var input = $(this),
//			numFiles = input.get(0).files ? input.get(0).files.length : 1,
//			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
//		input.trigger('fileselect', [numFiles, label]);
//	});
//
//	$(document).ready(function () {
//		$('.user-profile-img').on('fileselect', function (event, numFiles, label) {
//
//			var input = $(this).parents('.form-group').find('.change-img-name'),
//				log = numFiles > 1 ? numFiles + ' files selected' : label;
//
//			if (input.length) {
//				input.val(log);
//			} else {
//				if (log) alert(log);
//			}
//
//		});
//	});


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
	
	// Add segments to a slider
	$.fn.addSliderSegments = function (amount, orientation) {
		return this.each(function () {
			if (orientation == "vertical") {
				var output = '',
					i;
				for (i = 1; i <= amount - 2; i++) {
					output += '<div class="ui-slider-segment" style="top:' + 100 / (amount - 1) * i + '%;"></div>';
				};
				$(this).prepend(output);
			} else {
				var segmentGap = 100 / (amount - 1) + "%",
					segment = '<div class="ui-slider-segment" style="margin-left: ' + segmentGap + ';"></div>';
				$(this).prepend(segment.repeat(amount - 2));
			}
		});
	};

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

		// Disable link clicks to prevent page scrolling
		$(document).on('click', 'a[href="#fakelink"]', function (e) {
			e.preventDefault();
		});

		// jQuery UI Datepicker
		$('#datepicker-start').datepicker({
			showOtherMonths: true,
			selectOtherMonths: true,
			dateFormat: "dd/mm/yy",
			yearRange: '-1:+1'
		}).prev('.btn').on('click', function (e) {
			e && e.preventDefault();
			$('#datepicker-start').focus();
		});
		$.extend($.datepicker, {
			_checkOffset: function (inst, offset, isFixed) {
				return offset
			}
		});

		$('#datepicker-end').datepicker({
			showOtherMonths: true,
			selectOtherMonths: true,
			dateFormat: "dd/mm/yy",
			yearRange: '-1:+1'
		}).prev('.btn').on('click', function (e) {
			e && e.preventDefault();
			$('#datepicker-end').focus();
		});
		$.extend($.datepicker, {
			_checkOffset: function (inst, offset, isFixed) {
				return offset
			}
		});

		$('#birthday').datepicker({
			showOtherMonths: true,
			selectOtherMonths: true,
			dateFormat: "dd/mm/yy",
			yearRange: '-1:+1'
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