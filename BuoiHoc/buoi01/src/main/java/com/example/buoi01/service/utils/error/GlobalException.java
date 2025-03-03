package com.example.buoi01.service.utils.error;

import com.example.buoi01.domain.response.ResponseData;
import com.example.buoi01.service.utils.error.messageCustomExcetion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
@Slf4j(topic="GlobalException")
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(messageCustomExcetion.class)
    public ResponseEntity<ResponseData> idNotfoundError(messageCustomExcetion ex) {

        ResponseData res = new ResponseData();
        int statusCode = HttpStatus.BAD_REQUEST.value();
        res.setStatus(statusCode);
        res.setErors("Không tim thấy sản phẩm");
        String message = ex.getMessage();
        res.setMessage(message);
        res.setData(null);
        return ResponseEntity.badRequest().body(res);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData> AllException(Exception ex) {
        ResponseData res = new ResponseData();
        int statusCode = HttpStatus.BAD_REQUEST.value();
        res.setStatus(statusCode);
        res.setErors("Lỗi nào đó");
        String message = ex.getMessage();
        res.setMessage(message);
        res.setData(null);
        return ResponseEntity.badRequest().body(res);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseData> NoResourceFound(NoResourceFoundException ex) {
        ResponseData res = new ResponseData();
        int statusCode = HttpStatus.BAD_REQUEST.value();
        res.setStatus(statusCode);
        res.setErors(" NoResourceFound");
        String message = ex.getMessage();
        res.setMessage(message);
        res.setData(null);
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseData>  HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ResponseData res = new ResponseData();
        int statusCode = HttpStatus.BAD_REQUEST.value();
        res.setStatus(statusCode);
        res.setErors(" NoResourceFound");
        String message = ex.getMessage();
        res.setMessage(message);
        res.setData(null);
        return ResponseEntity.badRequest().body(res);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseData>  MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ResponseData res = new ResponseData();
        int statusCode = HttpStatus.BAD_REQUEST.value();
        res.setStatus(statusCode);
        res.setErors(" NoResourceFound");
        String message = ex.getMessage();
        res.setMessage(message);
        res.setData(null);
        return ResponseEntity.badRequest().body(res);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseData>  MethodArgumentTypeMismatchException(ResponseStatusException ex) {
        ResponseData res = new ResponseData();
        int statusCode = HttpStatus.BAD_REQUEST.value();
        res.setStatus(statusCode);
        res.setErors(" NoResourceFound");
        String message = ex.getMessage();
        res.setMessage(message);
        res.setData(null);
        return ResponseEntity.badRequest().body(res);
    }

}
