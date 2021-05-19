package com.example.springdemo.data;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.*;

@Table("aspnet_users")
public class AppUser {
    @Column("id")
    private Long id;
    public Long getId() { return id; }
    public void setId(Long value) { id = value; }

    @Column("user_name")
    private String userName;
    public String getUserName() { return userName; }
    public void setUserName(String value) { userName = value; }

    @Column("normalized_user_name")
    private String normalizedUserName;
    public String getNormalizedUserName() { return normalizedUserName; }
    public void setNormalizedUserName(String value) { normalizedUserName = value; }

    @Column("email")
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String value) { email = value; }

    @Column("normalized_email")
    private String normalizedEmail;
    public String getNormalizedEmail() { return normalizedEmail; }
    public void setNormalizedEmail(String value) { normalizedEmail = value; }

    @Column("email_confirmed")
    private Boolean emailConfirmed;
    public Boolean getEmailConfirmed() { return emailConfirmed; }
    public void setEmailConfirmed(Boolean value) { emailConfirmed = value; }

    @Column("phone_number")
    private String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String value) { phoneNumber = value; }

    @Column("phone_number_confirmed")
    private Boolean phoneNumberConfirmed;
    public Boolean getPhoneNumberConfirmed() { return phoneNumberConfirmed; }
    public void setPhoneNumberConfirmed(Boolean value) { phoneNumberConfirmed = value; }

    @Column("lockout_enabled")
    private Boolean lockoutEnabled;
    public Boolean getLockoutEnabled() { return lockoutEnabled; }
    public void setLockoutEnabled(Boolean value) { lockoutEnabled = value; }

    @Column("lockout_end_unix_time_seconds")
    private Long lockoutEndUnixTimeSeconds;
    public Long getLockoutEndUnixTimeSeconds() { return lockoutEndUnixTimeSeconds; }
    public void setLockoutEndUnixTimeSeconds(Long value) { lockoutEndUnixTimeSeconds = value; }

    @Column("password_hash")
    private String passwordHash;
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String value) { passwordHash = value; }

    @Column("access_failed_count")
    private int accessFailedCount;
    public int getAccessFailedCount() { return accessFailedCount; }
    public void setAccessFailedCount(int value) { accessFailedCount = value; }

    @Column("security_stamp")
    private String securityStamp;
    public String getSecurityStamp() { return securityStamp; }
    public void setSecurityStamp(String value) { securityStamp = value; }

    @Column("two_factor_enabled")
    private Boolean twoFactorEnabled;
    public Boolean getTwoFactorEnabled() { return twoFactorEnabled; }
    public void setTwoFactorEnabled(Boolean value) { twoFactorEnabled = value; }

    @Column("concurrency_stamp")
    private String concurrencyStamp;
    public String getConcurrencyStamp() { return concurrencyStamp; }
    public void setConcurrencyStamp(String value) { concurrencyStamp = value; }

}
