/*
 * Service for processing user information
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.DB.PDB;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private PDB pdb = new PDB();
    public long getUserIdViaTelegramId(long telegramId) {
        return pdb.getUserIdViaTelegramId(telegramId);
    }
}
