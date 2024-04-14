package com.example.product.commons;

import lombok.Builder;

@Builder
public record UniversalResponse (int status,String message, Object data){
}
