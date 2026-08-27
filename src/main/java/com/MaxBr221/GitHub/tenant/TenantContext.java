package com.MaxBr221.GitHub.tenant;

import com.MaxBr221.GitHub.model.Proprietario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class TenantContext {
    private static final ThreadLocal<Long> TENANT_ID =
            new ThreadLocal<>();

    public static Long getTenantId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long proprietarioId = ((Proprietario) authentication.getPrincipal()).getId();

        return proprietarioId;
    }
    public static void setTenantId(Long tenantId) {
        TENANT_ID.set(tenantId);
    }
}
