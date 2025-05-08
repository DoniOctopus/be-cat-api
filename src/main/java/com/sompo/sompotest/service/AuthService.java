package com.sompo.sompotest.service;

import com.sompo.sompotest.dto.AuthRequest;
import com.sompo.sompotest.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
