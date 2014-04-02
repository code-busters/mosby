<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<script>
    (function ($) {
        $(document).ready(function () {
            $('#free-ticket').click(function () {
                var id = getLastId();
                var priceInput = '<input type="text" class="form-control" value="<fmt:message key="ticketsPromoCodes.free"/>" name="event_ticket_price_' + id + '" id="event-ticket-price-' + id + '" readonly="" />';
                addTicket(priceInput);
            });

            $('#paid-ticket').click(function () {
                var id = getLastId();
                var priceInput = '<input type="number" class="form-control" placeholder="0" name="event_ticket_price_' + id + '" id="event-ticket-price-' + id + '" min="0" />';
                addTicket(priceInput);
            });

            $('#donation-ticket').click(function () {
                var id = getLastId();
                var priceInput = '<input type="text" class="form-control" value="<fmt:message key="ticketsPromoCodes.donation"/>" name="event_ticket_price_' + id + '" id="event-ticket-price-' + id + '" readonly="" />';
                addTicket(priceInput);
            });

            var addTicket = function (priceInput) {
                var id = getLastId();
                var tickets = $('#tickets > #tickets-body:last');
                tickets.append(
                        '<div id="' + id + '" class="row">' +
                                '<input class="hide" type="text" name="ticket_id" value="'+ id +'" > ' +
                                '<div class="col-md-6 col-sm-6">' +
                                '<label class="visible-xs" for="event-ticket-name-' + id + '"><fmt:message key="ticketsPromoCodes.ticketName"/></label>' +
                                '<input type="text" class="form-control" placeholder="<fmt:message key="ticketsPromoCodes.examplesEarlyBirdVipPress"/>" name="event_ticket_name_' + id + '" id="event-ticket-name-' + id + '" required />' +
                                '</div>' +
                                '<div class="col-md-2 col-sm-2">' +
                                '<label class="visible-xs" for="event-ticket-quantity-' + id + '"><fmt:message key="ticketsPromoCodes.quantityAvailable"/></label>' +
                                '<input type="number" class="form-control" placeholder="100" name="event_ticket_quantity_' + id + '" id="event-ticket-quantity-' + id + '" min="1" />' +
                                '</div>' +
                                '<div class="col-md-2 col-sm-2">' +
                                '<label class="visible-xs" for="event-ticket-price-' + id + '"><fmt:message key="ticketsPromoCodes.price"/></label>' +
                                priceInput +
                                '</div>' +
                                '<div class="col-md-2 col-sm-2 actions text-center">' +
                                '<label class="visible-xs text-left"><fmt:message key="ticketsPromoCodes.actions"/></label>' +
                                '<a id="open-falldown" href="#fakelink">' +
                                '<span class="fui-gear"></span>' +
                                '</a>' +
                                '<a class="delete-nearby-row" href="#">' +
                                '<span class="fui-trash"></span>' +
                                '</a>' +
                                '</div>' +
                                '<div id="falldown-' + id + '" class="falldown col-md-12">' +
                                '<div class="form-group">' +
                                '<label for="ticket-description-' + id + '"><fmt:message key="ticketsPromoCodes.ticketDescription"/></label>' +
                                '<textarea rows="3" placeholder="<fmt:message key="ticketsPromoCodes.additionalInfoAboutTicket"/>" class="form-control" id="ticket-description-' + id + '" form="create-event-form"></textarea>' +
                                '</div>' +
                                '<div class="form-group">' +
                                '<label for="ticket-datepicker-start-' + id + '"><fmt:message key="ticketsPromoCodes.startDateTimeForTicket"/></label>' +
                                '<div class="input-prepend input-datepicker">' +
                                '<button type="button" class="btn">' +
                                '<span class="fui-calendar"></span>' +
                                '</button>' +
                                '<input type="text" placeholder="<fmt:message key="ticketsPromoCodes.startDate"/>" name="ticket_start_date_' + id + '" id="ticket-datepicker-start-' + id + '" readonly="" required>' +
                                '</div>' +
                                '<input type="time" class="form-control time" value="00:00" name="ticket_start_time_' + id + '" required>' +
                                '</div>' +
                                '<div class="form-group">' +
                                '<label for="ticket-datepicker-end-' + id + '"><fmt:message key="ticketsPromoCodes.endDateTimeForTicket"/></label>' +
                                '<div class="input-prepend input-datepicker">' +
                                '<button type="button" class="btn">' +
                                '<span class="fui-calendar"></span>' +
                                '</button>' +
                                '<input type="text" placeholder="<fmt:message key="ticketsPromoCodes.endDate"/>" name="ticket_end_date_' + id + '" id="ticket-datepicker-end-' + id + '" readonly="" required>' +
                                '</div>' +
                                '<input type="time" class="form-control time" value="00:00" name="ticket_end_time_' + id + '" required>' +
                                '</div>' +
                                '<div class="form-group">' +
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
                                '<input class="hide" type="text" name="promo_code_id" value="'+ id +'""> ' +
                                '<div class="col-md-6 col-sm-6">' +
                                '<label class="visible-xs" for="promo-code-code-' + id + '"><fmt:message key="ticketsPromoCodes.promoCode"/></label>' +
                                '<input type="text" class="form-control" placeholder="<fmt:message key="ticketsPromoCodes.examplesR2D2"/>" name="promo_code_code_' + id + '" id="promo-code-code-' + id + '" required />' +
                                '</div>' +
                                '<div class="col-md-2 col-sm-2">' +
                                '<label class="visible-xs" for="promo-code-quantity-' + id + '"><fmt:message key="ticketsPromoCodes.quantityAvailable"/></label>' +
                                '<input type="number" class="form-control" placeholder="5" name="promo_code_quantity_' + id + '" id="promo-code-quantity-' + id + '" min="1" />' +
                                '</div>' +
                                '<div class="col-md-2 col-sm-2">' +
                                '<label class="visible-xs" for="promo-code-discount-' + id + '"><fmt:message key="ticketsPromoCodes.discount"/></label>' +
                                '<input type="number" class="form-control" placeholder="10" name="promo_code_discount_' + id + '" id="promo-code-discount-' + id + '" min="0" />' +
                                '</div>' +
                                '<div class="col-md-2 col-sm-2 actions text-center">' +
                                '<label class="visible-xs text-left"><fmt:message key="ticketsPromoCodes.actions"/></label>' +
                                '<a id="open-falldown" href="#fakelink">' +
                                '<span class="fui-gear"></span>' +
                                '</a>' +
                                '<a class="delete-nearby-row" href="#">' +
                                '<span class="fui-trash"></span>' +
                                '</a>' +
                                '</div>' +
                                '<div id="falldown-' + id + '" class="falldown col-md-12">' +
                                '<div class="form-group">' +
                                '<label for="promo-code-description-' + id + '"><fmt:message key="ticketsPromoCodes.promoCodeDescription"/></label>' +
                                '<textarea rows="3" placeholder="<fmt:message key="ticketsPromoCodes.additionalInfoAboutPromoCode"/>" class="form-control" name="promo_code_description_' + id + '" id="promo-code-description-' + id + '" form="create-event-form"></textarea>' +
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
</script>