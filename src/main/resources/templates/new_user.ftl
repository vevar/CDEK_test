<#import "parts/common.ftl" as c>

<@c.page>
<div>
    <#if message??>
        <div>
            ${message}
        </div>
    </#if>

    <form action="/user/new_user" method="post">
        <div class="input-group">
            <input class="form-control" type="text" name="name" placeholder="Input username">
            <div class="input-group-append">
                <input class="btn btn-outline-secondary" type="submit" value="Add">
            </div>

        </div>
    </form>
</div>
</@c.page>