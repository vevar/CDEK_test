<#import "parts/common.ftl" as c>

<@c.page>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>

<div>
    <form action="/user" method="get">
        <div class="input-group mb-3">
            <input class="form-control" type="text" name="username" value="${username}"
                   placeholder="Username">
            <div class="input-group-append">
                <input class="btn btn-outline-secondary" type="submit" value="Search">

            </div>
        </div>

    </form>

    <#if users??>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                </tr>
            </#list>
        </table>
    <#else>
        Users not exist.
    </#if>
</div>
</@c.page>