//
//  ServiceSpotcheckApi.m
//  Spotcheck-iOS
//
//  Created by Matthew Reiss on 04/09/16.
//  Copyright © 2016 Matthew Reiss. All rights reserved.
//
// Service:
//   SpotcheckApi/v1
// Description:
//   This is an API
// Classes:
//   ServiceSpotcheckApi (0 custom class methods, 0 custom properties)

#import "SpotcheckApi.h"

@implementation ServiceSpotcheckApi

#if DEBUG
// Method compiled in debug builds just to check that all the needed support
// classes are present at link time.
+ (NSArray *)checkClasses {
    NSArray *classes = @[
                         [QuerySpotcheckApi class],
                         [Account class]
                         ];
    return classes;
}
#endif  // DEBUG

- (instancetype)init {
    self = [super init];
    if (self) {
        // Version from discovery.
        self.apiVersion = @"v1";
        
        // From discovery.  Where to send JSON-RPC.
        // Turn off prettyPrint for this service to save bandwidth (especially on
        // mobile). The fetcher logging will pretty print.
        self.rpcURL = [NSURL URLWithString:@"https://spotcheck-it.appspot.com/_ah/api/rpc?prettyPrint=false"];
    }
    return self;
}

@end
