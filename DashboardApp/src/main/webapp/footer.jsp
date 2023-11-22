<style>
    .footer {
        position: fixed;
        bottom: 0;
        width:100%;
        height: 40px;
        background-color: deepskyblue;
    }
</style>
<%
    java.time.Year currentYear = java.time.Year.now();
%>
<footer class="footer font-small black">
    <div class="footer-copyright text-center py-3" style="color: white"><%=currentYear.getValue()%> Copyright .
        <i>Ramin Allahverdiyev</i>

    </div>
</footer>