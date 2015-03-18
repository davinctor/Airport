<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<spring:url value="/webjars/bootstrap/3.3.1/css/bootstrap.min.css" var="bootstrapCss"/>
<link href="${bootstrapCss}" rel="stylesheet"/>

<spring:url value="/resources/css/font-awesome/css/font-awesome.min.css" var="fontAw"/>
<link href="${fontAw}" rel="stylesheet">

<spring:url value="/resources/css/bootstrap-datepicker/bootstrap-datetimepicker.css" var="bootDateCss"/>
<link href="${bootDateCss}" rel="stylesheet">

<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQueryUrl"/>
<script type="text/javascript" src="${jQueryUrl}"></script>

<spring:url value="/resources/css/airport.css" var="airportCss"/>
<link href="${airportCss}" rel="stylesheet"/>

<spring:url value="/webjars/bootstrap/3.3.1/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}" type="text/javascript"></script>

<spring:url value="/resources/js/bootstrap-datepicker/moment-with-locales.js" var="MomentWithLocaleForBootDateJS"/>
<script src="${MomentWithLocaleForBootDateJS}" type="text/javascript"></script>

<spring:url value="/resources/js/bootstrap-datepicker/bootstrap-datetimepicker.js" var="bootDateJs"/>
<script src="${bootDateJs}" type="text/javascript"></script>

<!-- jquery-ui.js file is really big so we only load what we need instead of loading everything -->
<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js" var="jQueryUiCore"/>
<script src="${jQueryUiCore}" type="text/javascript"></script>

<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.datepicker.js" var="jQueryUiDatePicker"/>
<script src="${jQueryUiDatePicker}" type="text/javascript"></script>

<!-- jquery-ui.css file is not that big so we can afford to load it -->
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css" var="jQueryUiCss"/>
<link href="${jQueryUiCss}" rel="stylesheet"/>

<spring:url value="/resources/js/airportscripts.js" var="airportScript"/>
<script src="${airportScript}" type="text/javascript"></script>