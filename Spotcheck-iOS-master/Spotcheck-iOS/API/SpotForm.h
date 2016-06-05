//
//  SpotForm.h
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

@interface SpotForm : GTLObject
@property (nonatomic, copy) NSNumber *profileId;
@property (nonatomic, copy) NSData *photo;
@property (nonatomic, copy) NSString *name;
@end
