package com.spotcheck.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import com.spotcheck.entity.Spot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import static com.spotcheck.service.OfyService.ofy;

/**
 * Created by Tung on 4/21/2016.
 */

@Api(name = "spotApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.spotcheck.com", ownerName = "api.spotcheck.com"))
public class SpotApi {


    @ApiMethod(name = "listSpot")
    public CollectionResponse<Spot> listSpot(@Nullable @Named("cursor") String cursorString,
                                             @Nullable @Named("count") Integer count) {

        Query<Spot> query = ofy().load().type(Spot.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<Spot> records = new ArrayList<>();
        QueryResultIterator<Spot> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

        //Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<Spot>builder().setItems(records).setNextPageToken(cursorString).build();
    }

    /**
     * This inserts a new Spot object.
     * @param spot The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertSpot")
    public Spot insertSpot(Spot spot) throws ConflictException {
        //If it is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        if (spot.getId() != null) {
            if (findRecord(spot.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(spot).now();
        return spot;
    }

    /**
     * This updates an existing Spot object.
     * @param spot The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateSpot")
    public Spot updateSpot(Spot spot)throws NotFoundException {
        if (findRecord(spot.getId()) == null) {
            throw new NotFoundException("Spot Record does not exist");
        }
        ofy().save().entity(spot).now();
        return spot;
    }

    /**
     * This deletes an existing Spot object.
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeSpot")
    public void removeSpot(@Named("id") Long id) throws NotFoundException {
        Spot record = findRecord(id);
        if(record == null) {
            throw new NotFoundException("Spot Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    @ApiMethod(name = "retrieveSpot")
    public Spot retrieveSpot(@Named("id") Long id){
        return ofy().load().type(Spot.class).id(id).now();
    }


    //Private method to retrieve a Spot record
    private Spot findRecord(Long id) {
        return ofy().load().type(Spot.class).id(id).now();
        //or return ofy().load().type(Spot.class).filter("id",id).first.now();
    }
}
