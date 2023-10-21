package com.realman.becore.service.autocomplete;

import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.autocomplete.models.AutoCompleteRequest;
import com.realman.becore.controller.api.autocomplete.models.AutoCompleteResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutoCompleteUseCaseService {
    
    @NonNull
    private final AutoCompleteRequestService autoCompleteRequestService;

    public AutoCompleteResponse requestPlaces(AutoCompleteRequest request) {
        return autoCompleteRequestService.requestPlaces(request);
    }

}
