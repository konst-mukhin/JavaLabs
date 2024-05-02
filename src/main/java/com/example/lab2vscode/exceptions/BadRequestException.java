package com.example.lab2vscode.exceptions;

public class BadRequestException extends Exception {
  public BadRequestException(String message) {
    super(message);
  }
}
