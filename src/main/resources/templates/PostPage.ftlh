<#-- @ftlvariable name="posts" type="ru.itmo.wp.domain.Post[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <div class="form">
                <div class="header">Write comment</div>
                <div class="body">
                    <form method="post" action="/post/${post.id}">
                        <div class="field">
                            <div class="value">
                                <label>
                                    <textarea name="text" value="${comment.text!}"></textarea>
                                </label>
                            </div>
                            <@c.error "comment.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Write">
                        </div>
                    </form>
                </div>
            </div>
        </#if>
        <#if post.comments?has_content>
            <div class="post-comments datatable">
                <div class="caption">Comments</div>
                <table>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Author</th>
                        <th>Text</th>
                        <th>Created Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if post.comments?size==0>
                        <tr class="noData">
                            <td colspan="4">
                                No data
                            </td>
                        </tr>
                    <#else>
                        <#list post.comments as comment>
                            <tr>
                                <td class="id">${comment.id}</td>
                                <td class="author">${comment.user.login}</td>
                                <td class="text">${comment.text}</td>
                                <td class="creationTime">${comment.creationTime}</td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </#if>
    <#else >
        No such post.
    </#if>
</@c.page>
