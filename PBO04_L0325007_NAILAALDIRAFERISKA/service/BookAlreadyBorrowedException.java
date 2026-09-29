/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author aldir
 */
public class BookAlreadyBorrowedException extends Exception {

    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}
