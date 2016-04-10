//
//  AccountForm.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   suggestionBeanApi/v1
// Description:
//   This is an API
// Classes:
//   Account (0 custom class methods, 1 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
#import "GTL/GTLObject.h"
#else
#import "GTLObject.h"
#endif

// ----------------------------------------------------------------------------
//
//   AccountForm
//

@interface AccountForm : GTLObject
@property (nonatomic, copy) NSString *firstName;
@property (nonatomic, copy) NSString *lastName;
@property (nonatomic, copy) NSString *email;
@property (nonatomic, copy) NSString *password;
@end
