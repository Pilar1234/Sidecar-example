function introspectAccessToken(r) {
    const requestToken = r.headersIn['Authorization']
    if (!requestToken) {
        r.return(401)
    } else {
        r.subrequest("/auth",  // takes the definition from nginx-server.conf
            function (reply) {
                if (reply.status === 200) {
                    r.return(200, reply.body);
                } else {
                    r.return(401);
                }
            }
        );
    }
}

export default {introspectAccessToken}