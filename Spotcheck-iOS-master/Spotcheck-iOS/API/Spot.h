//
//  Spot.h
//  Spotcheck-iOS
//
//  Created by Matthew Reiss on 5/30/16.
//  Copyright © 2016 Spotcheck. All rights reserved.
//

#if GTL_BUILT_AS_FRAMEWORK
#import "GTL/GTLObject.h"
#else
#import "GTLObject.h"
#endif


// ----------------------------------------------------------------------------
//
//   Spot
//

@interface Spot : GTLObject
@property (nonatomic, copy) NSNumber *spotId;
@property (nonatomic, copy) NSNumber *profileId;
@property (nonatomic, copy) NSObject *photo;
@property (nonatomic, copy) NSString *name;

//needs these to match json results
@property (nonatomic, copy) NSString *etag;
@property (nonatomic, copy) NSString *kind;

@end