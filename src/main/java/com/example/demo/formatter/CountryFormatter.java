package com.example.demo.formatter;

import com.example.demo.model.Country;
import com.example.demo.service.Country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class CountryFormatter implements Formatter<Country> {
    @Autowired
    ICountryService countryService;
    @Override
    public Country parse(String text, Locale locale) throws ParseException {
        return countryService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Country object, Locale locale) {
        return null;
    }
}
