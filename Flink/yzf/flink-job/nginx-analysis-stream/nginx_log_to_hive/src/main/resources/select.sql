select 
    upstream_addr,
    body_bytes_sent,
    ssl_cipher,
    source,
    proxy_add_x_forwarded_for,
    remote_user,
    request,
    request_time,
    time_local,
    http_user_agent,
    http_referer,
    remote_addrx,
    nginx,
    scheme,
    status,
    upstream_addr_nm,
    x_forwarded_for,
    cookie_gs_id,
    cookie_phone,
    cookie_user_id,
    cookie_user_name,
    ua_browser_version_info,
    ua_device_type,
    ua_os_family,
    ua_os_name,
    ua_type,
    ua_ua_family,
    ua_ua_name,
    time_local_ts,
    gsmc,
    gslx,
    substr(time_local_ts,1,8) as dt
from source_table as s
left join flink_database.dim_dzgs_d for system_time as of s.proctime as d
on s.cookie_gs_id = d.gsid