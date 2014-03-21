(function ($) {
	$(document).ready(function () {
		$('#free-ticket').click(function () {
			var id = getLastId();
			var priceInput = '<input type="text" class="form-control" value="Free" name="event_ticket_price_' + id + '" id="event-ticket-price-' + id + '" readonly="" disabled="disabled" />';
			addTicket(priceInput);
		});

		$('#paid-ticket').click(function () {
			var id = getLastId();
			var priceInput = '<input type="number" class="form-control" placeholder="0" name="event_ticket_price_' + id + '" id="event-ticket-price-' + id + '" min="0" />';
			addTicket(priceInput);
		});

		$('#donation-ticket').click(function () {
			var id = getLastId();
			var priceInput = '<input type="text" class="form-control" value="Donation" name="event_ticket_price_' + id + '" id="event-ticket-price-' + id + '" readonly="" disabled="disabled" />';
			addTicket(priceInput);
		});

		var addTicket = function (priceInput) {
			var id = getLastId();
			var tickets = $('#tickets > #tickets-body:last');
			tickets.append(
				'<div id="' + id + '" class="row">' +
				'<div class="col-md-6 col-sm-6">' +
				'<label class="visible-xs" for="event-ticket-name-' + id + '">Ticket name</label>' +
				'<input type="text" class="form-control" placeholder="Examples: Early Bird, VIP, Press" name="event_ticket_name_' + id + '" id="event-ticket-name-' + id + '" />' +
				'</div>' +
				'<div class="col-md-2 col-sm-2">' +
				'<label class="visible-xs" for="event-ticket-quantity-' + id + '">Quantity available</label>' +
				'<input type="number" class="form-control" placeholder="100" name="event_ticket_quantity_' + id + '" id="event-ticket-quantity-' + id + '" min="1" />' +
				'</div>' +
				'<div class="col-md-2 col-sm-2">' +
				'<label class="visible-xs" for="event-ticket-price-' + id + '">Price</label>' +
				priceInput +
				'</div>' +
				'<div class="col-md-2 col-sm-2 actions text-center">' +
				'<label class="visible-xs text-left">Actions</label>' +
				'<a id="open-falldown" href="#fakelink">' +
				'<span class="fui-gear"></span>' +
				'</a>' +
				'<a class="delete-nearby-row" href="#">' +
				'<span class="fui-trash"></span>' +
				'</a>' +
				'</div>' +
				'<div id="falldown-' + id + '" class="falldown col-md-12">' +
				'<div class="form-group">' +
				'<label for="ticket-description-' + id + '">Ticket Description</label>' +
				'<textarea rows="3" placeholder="Additional info about ticket" class="form-control" id="ticket-description-' + id + '" form="create-event-form"></textarea>' +
				'</div>' +
				'<div class="form-group">' +
				'<label for="ticket-datepicker-start-' + id + '">Start Date &amp; Time For Ticket</label>' +
				'<div class="input-prepend input-datepicker">' +
				'<button type="button" class="btn">' +
				'<span class="fui-calendar"></span>' +
				'</button>' +
				'<input type="text" placeholder="Start date" name="ticket_start_date_' + id + '" id="ticket-datepicker-start-' + id + '" readonly="" required>' +
				'</div>' +
				'<input type="time" class="form-control time" value="00:00" name="ticket_start_time_' + id + '" required>' +
				'</div>' +
				'<div class="form-group">' +
				'<label for="ticket-datepicker-end-' + id + '">End Date &amp; Time For Ticket</label>' +
				'<div class="input-prepend input-datepicker">' +
				'<button type="button" class="btn">' +
				'<span class="fui-calendar"></span>' +
				'</button>' +
				'<input type="text" placeholder="Start date" name="ticket_end_date_' + id + '" id="ticket-datepicker-end-' + id + '" readonly="" required>' +
				'</div>' +
				'<input type="time" class="form-control time" value="00:00" name="ticket_end_time_' + id + '" required>' +
				'</div>' +
				'<div class="form-group">' +
				'<span>Tickets Allowed Per Order</span>' +
				'<label class="additional" for="ticket-min-per-order-' + id + '">Min</label>' +
				'<input type="number" class="form-control" placeholder="1" value="1" name="ticket_min_per_order_' + id + '" id="ticket-min-per-order-' + id + '" min="1" />' +
				'<label class="additional" for="ticket-max-per-order-' + id + '">Max</label>' +
				'<input type="number" class="form-control" placeholder="1" name="ticket_max_per_order_' + id + '" id="ticket-max-per-order-' + id + '" min="1" />' +
				'</div>' +
				'</div>' +
				'</div>'
			);

			$('[id*="datepicker"]').datepicker({
				showOtherMonths: true,
				selectOtherMonths: true,
				dateFormat: "dd-mm-yy",
				changeMonth: 1,
				changeYear: 1,
				minDate: '0d',
				yearRange: 'c:+3'
			}).prev('.btn').on('click', function (e) {
				e && e.preventDefault();
				$('[id*="datepicker"]').focus();
			});
			$.extend($.datepicker, {
				_checkOffset: function (inst, offset, isFixed) {
					return offset
				}
			});
			return this;
		};

		$('#add-promo-code').click(function () {
			var stringId = $('#promo-codes > #promo-codes-body:last .row:last').attr('id');
			var id = 0;
			if (stringId != 'undefined' && !isNaN(stringId)) {
				id = parseInt(stringId) + 1;
			}
			$('#promo-codes > #promo-codes-body:last').append(
				'<div id="' + id + '" class="row">' +
				'<div class="col-md-6 col-sm-6">' +
				'<label class="visible-xs" for="promo-code-code-' + id + '">Promo code</label>' +
				'<input type="text" class="form-control" placeholder="Examples: #Event_code32, R2D2" name="promo_code_code_' + id + '" id="promo-code-code-' + id + '" />' +
				'</div>' +
				'<div class="col-md-2 col-sm-2">' +
				'<label class="visible-xs" for="promo-code-quantity-' + id + '">Quantity available</label>' +
				'<input type="number" class="form-control" placeholder="5" name="promo_code_quantity_' + id + '" id="promo-code-quantity-' + id + '" min="1" />' +
				'</div>' +
				'<div class="col-md-2 col-sm-2">' +
				'<label class="visible-xs" for="promo-code-discount-' + id + '">Discount</label>' +
				'<input type="number" class="form-control" placeholder="10%" name="promo_code_discount_' + id + '" id="promo-code-discount-' + id + '" min="0" />' +
				'</div>' +
				'<div class="col-md-2 col-sm-2 actions text-center">' +
				'<label class="visible-xs text-left">Actions</label>' +
				'<a id="open-falldown" href="#fakelink">' +
				'<span class="fui-gear"></span>' +
				'</a>' +
				'<a class="delete-nearby-row" href="#">' +
				'<span class="fui-trash"></span>' +
				'</a>' +
				'</div>' +
				'<div id="falldown-' + id + '" class="falldown col-md-12">' +
				'<div class="form-group">' +
				'<label for="promo-code-description-' + id + '">Promo Code Description</label>' +
				'<textarea rows="3" placeholder="Additional info about promo code" class="form-control" name="promo_code_description_' + id + '" id="promo-code-description-' + id + '" form="create-event-form"></textarea>' +
				'</div>' +
				'</div>' +
				'</div>'
			);
		});

		var getLastId = function () {
			var stringId = $('#tickets > #tickets-body:last .row:last').attr('id');
			var id = 0;
			if (stringId != 'undefined' && !isNaN(stringId)) {
				id = parseInt(stringId) + 1;
			}
			return id;
		};

	});

	$(document).on('click', ".delete-nearby-row", function () {
		var row = $(this).closest('.row');
		row.fadeOut(400, function () {
			row.remove();
		});
		return false;
	});

	$(document).on('click', '[name="submit"]', function () {
		setIdsArray('tickets');
		setIdsArray('promo-codes');
	});

	var setIdsArray = function (value) {
		var ids = [];
		$('#' + value + ' > #' + value + '-body:last').find('.row').each(function () {
			ids.push(this.id);
		});
		var temp = ids.join('_');
		value = value.replace('-', '_');
		console.log(value + '  ' + temp);
		$('[name="' + value + '_id"]').val(temp);
	};

})(jQuery);