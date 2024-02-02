package uz.zako.zakoBackend.service;

import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.ConnectionPayload;

public interface ConnectionService {
    Result saveConnection (ConnectionPayload connectionPayload);
    Result deleteConnection (Long id);
    Result editConnection (Long id, ConnectionPayload connectionPayload);
    Result getConnection ();
    Result findConnection(Long id);
}
